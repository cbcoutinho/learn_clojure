(ns brave-clojure.pure
  (:require [clojure.string :as s]))

(defn clean [text]
  (s/replace (s/trim text) #"lol" "LOL"))

(def character
  {:name "Smooches McCutes"
   :attributes {:intelligence 10
                :strength      4
                :dexterity     5}})

; NOTE: These were overwritten by the `defattrs` macro below for
; Exercise 3 of Chapter 7
; Create some composition functions to string pure functions together
;(def c-int (comp :intelligence :attributes))
;(def c-str (comp :strength     :attributes))
;(def c-dex (comp :dexterity    :attributes))

; Exercise 1: Make a factory that creates a function using the 
; following signature:
;   (attr :intelligence)
(defn attr [my-key]
  "Returns a function to quickly look up a characters attribute"
  (def myfun (comp my-key :attributes)))

; Exercise 2: Implement the `comp` function
(defn my-comp [f g]
  "My own version of the `comp` function. Takes in two functions and
  returns a new function. Essentially my-comp(f,g) = f(g(x))"
  (fn [& args] (f (apply g args))))


; Exercise 3: Attempt to create macro for setting character functions
;   https://www.braveclojure.com/writing-macros/

(defmacro defattrs
  ([] nil)  ; If empty list, return nothing
  ([x y]    ; De-structure a two element list and compose function
   `(def ~x (comp ~y :attributes))) 
  ([x y & next] ; If number elements > 2, split them up and recurse down
   `(do
     (defattrs ~x ~y)
     (defattrs ~@next))))

; Shows that `defattrs` creates some composition functions
(macroexpand '(defattrs c-int :intelligence
                        c-str :strength
                        c-dex :dexterity))

; Actually create the functions using the macro..
(defattrs c-int :intelligence
          c-str :strength
          c-dex :dexterity)
