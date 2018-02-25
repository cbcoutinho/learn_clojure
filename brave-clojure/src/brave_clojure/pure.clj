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

; Test solution for Ex1
(and
  (=  
    ((attr :intelligence) character)
    (c-int character))
  (= 
    ((attr :strength) character)
    (c-str character))
  (=
    ((attr :dexterity) character)
    (c-dex character)))
