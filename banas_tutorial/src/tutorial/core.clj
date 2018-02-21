(ns tutorial.core
  (:require [clojure.string :as str])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (def str1 "This is my 2nd String")
  (str/blank? str1)
  (str/includes? str1 "second")
  (str/split str1 #" ")
  (str/split str1 #"\d")
  (str/replace "I am 42" #"42" "43")
  (str/upper-case str1)
  (str/lower-case str1)

  (println (list "Dog" 1 3.41 true))
  (println (first (list 1 2 3))))

