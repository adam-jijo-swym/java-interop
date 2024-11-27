(ns jinterop.random
  (:require [jinterop.defjava :refer [defjava]]))

(defjava myrandom String [String s] (str s (rand-int 100)))
(prn "myrandom clojure returns" (myrandom "fromclojure"))

(defjava mynotrandom String [String s] (str s 50))
(prn "myrandom clojure returns" (myrandom "fromclojure"))
(prn "mynotrandom clojure returns" (mynotrandom "fromclojure"))
