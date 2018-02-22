(ns tutorial.core
  (:require [clojure.string :as str])
  (:gen-class))

(use 'clojure.java.io)

; Struct maps
(defn struct-map-ex
  []
  (defstruct Customer :Name :Phone)
  (def cust1 (struct Customer "Chris" 1234567))
  (def cust2 (struct-map Customer :Name "Sally"
                                  :Phone 1234567))
  (println cust1)
  (println cust2)
  (println (format "Cust1 :Name is %s" (:Name cust1))))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (struct-map-ex))
