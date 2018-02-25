(ns brave-clojure.pure
  (:require [clojure.string :as s]))

(defn clean [text]
  (s/replace (s/trim text) #"lol" "LOL"))
             
(clean "My boa constrictor is so sassy lol!    ")

(def character
  {:name "Smooches McCutes"
   :attributes {:intelligence 10
                :strength      4
                :dexterity     5}})

; Create some composition functions to string pure functions together
(def c-int (comp :intelligence :attributes))
(def c-str (comp :strength     :attributes))
(def c-dex (comp :dexterity    :attributes))

; Get attributes of `character` using composition functions
;   Essentially:
;   (= (c-str character) 
;      ((fn [c] (:strength (:attributes c))) character))
(:attributes character)
(c-int character)
(c-str character)
(c-dex character)

; Exercise 1: Make a function factory that creates a function using
; the following signature:
;   (attr :intelligence)
(defn attr [my-key]
  "Returns a function to quickly look up a characters attribute"
  (def myfun (comp my-key :attributes)))

; Exercise 2: Implement the `comp` function
(defn my-comp [f g]
  "My own version of the `comp` function. Takes in two functions and
  returns a new function. Essentially my-comp(f,g) = f(g(x))"
  (fn [& args] (f (apply g args))))

((comp inc +) 2 3)
((my-comp inc +) 2 3)
