(ns tutorial.core
  (:require [clojure.string :as str])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]

  ; Strings
  (def str1 "This is my 2nd String")
  (str/blank? str1)
  (str/includes? str1 "second")
  (str/split str1 #" ")
  (str/split str1 #"\d")
  (str/replace "I am 42" #"42" "43")
  (str/upper-case str1)
  (str/lower-case str1)

  ; Lists
  (println (list "Dog" 1 3.41 true))
  (println (nth (list 1 2 3) 0))
  (println (cons 3 (list 1 2)))

  ; Sets
  (println (get (set '(1 1 2)) 2))
  (println (get (set '(3 2)) 2))
  (println (contains? (set '(1 2 3)) 4))
  (println (disj (set '(1 2 3)) 3))

  ; Vectors
  (println (get (vector 3 2 1) 2))
  (println (pop (vector 1 2 3 4)))
  (println (subvec (vector 1 2 3 4) 1 3))

  ; Maps
  (def my-map (hash-map "Name" "Chris" "Age" 26))
  (println my-map)
  (println (sorted-map 2 "B" 1 "A" 4 "D" 3 "C"))
  (println (get my-map "Name")) ; Only returns value
  (println (apply hash-map (find my-map "Name"))) ; Returns both key and value
  (println (contains? my-map "Name"))
  (println (keys my-map))
  (println (vals my-map))
  (def my-map2 (merge-with + 
                       (apply hash-map (find my-map "Name")) 
                       (apply hash-map (find my-map "Age"))))
  (println (= my-map my-map2)))
