(ns jinterop.random
  (:require [jinterop.defjava :refer [def-java-method]]))

; (defn myrandom [s] (str s (rand-int 100)))
(def-java-method myrandom String [String s] (str s (rand-int 100)))
(prn "myrandom clojure returns" (myrandom "fromclojure"))

(def-java-method mynotrandom String [String s] (str s 50))
(prn "myrandom clojure returns" (myrandom "fromclojure"))
(prn "mynotrandom clojure returns" (mynotrandom "fromclojure"))
