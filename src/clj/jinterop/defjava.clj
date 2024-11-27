(ns jinterop.defjava)

(defn- qualify-sym [sym] (symbol (str *ns*) (str sym)))

(defn- get-alternate-elements [args] (vec (flatten (partition 1 2 args))))

(def ^:private java-methods (atom #{}))

(defn- add-method
  [method-name arg-types return-type]
  (swap! java-methods conj [method-name arg-types return-type]))

(defmacro defjava
  [method-name return-type args & body]
  (let [arg-types#    (get-alternate-elements args)
        fn-args#      (get-alternate-elements (rest args))
        java-methods# (map #(with-meta % {:static true})
                           (add-method method-name arg-types# return-type))]
    `(do (gen-class :name    com.swym.Instrumented
                    :prefix  "defjava-"
                    :methods ~java-methods#)
         (let [prefixed-method-name# (symbol (str "defjava-" '~method-name))
               qualified-prefixed-method-name# (~qualify-sym
                                                prefixed-method-name#)]
           (intern
            *ns*
            prefixed-method-name#
            (fn ~fn-args# (prn "in" prefixed-method-name# ~fn-args#) ~@body))
           (intern *ns*
                   '~method-name
                   (resolve qualified-prefixed-method-name#))))))

(comment
  (let [method-name 'trial-method
        prefixed-method-name (symbol (str "-" method-name))
        qualified-prefixed-method-name (qualify-sym prefixed-method-name)]
    (intern *ns*
            prefixed-method-name
            (fn [a] (prn "in" prefixed-method-name a)))
    (intern *ns* method-name (resolve qualified-prefixed-method-name)))
  (-trial-method "hello")
  (trial-method "hello")
  (add-method 'hello [String String] String)
  (add-method 'hello-2 [String String] String)
  [^:static (vec @java-methods)]
  (with-meta (vec @java-methods) {:static true})
  (conj '(2 3 4) 1))
