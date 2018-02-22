(ns tutorial.core
  (:require [clojure.string :as str])
  (:gen-class))

(defn say-hello
  "Receives a name with 1 parameter and responds with 'Hello'"
  [name]
  (println "Hello again " name))

; Adds two numbers together
(defn get-sum
  [x y]
  (println (+ x y)))

; Uses an interface to add either two or three numbers together
(defn get-sum-more
  ([x y z]
   (println (+ x y z)))
  ([x y]
   (get-sum x y))) ; `println` is already implemented in get-sum

; Creates a "Hello <name>" String
(defn hello-you
  [name]
  (str "Hello " name))

; Says hello to each name in a list
(defn hello-all
  [& names]
  (println (map hello-you names)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (say-hello "Chris")
  (get-sum 4 5)
  (get-sum-more 3 4 5)
  (get-sum-more 3 4)
  (hello-all "Doug" "Mary" "Paul"))
