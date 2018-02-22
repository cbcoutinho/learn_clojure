(ns clojure-noob.core
  (:gen-class))

(defmacro check-mod
  "This macro returns a function which calculates (= (mod _ x) 0)"
  [x]
  (fn [i] (zero? (mod i x))))

; Four fizzbuzz implementations from here:
; http://eddmann.com/posts/fizzbuzz-in-clojure/

; Original function definitions included anonymous functions, I
; switched those to using a macro
;(def fizz? #(zero? (mod % 3)))
(def fizz? (check-mod 3))
(def buzz? (check-mod 5))
(def fizzbuzz? (check-mod 15))

(def fizzbuzz1
  "Returns an anonymous function that contains a conditional"
  #(cond
     (fizzbuzz? %) "FizzBuzz"
     (fizz? %) "Fizz"
     (buzz? %) "Buzz"
     :else %))

(defn fizzbuzz2 [n]
  "This function builds a string. If empty, returns a number"
  (let [s (str (if (fizz? n) "Fizz") (if (buzz? n) "Buzz"))]
    (if (empty? s) n s)))


(defn fizzbuzz3 [n]
  "Similar to the 'cond' approach in fizzbuzz1"
  (let [to-words (some-fn #(when (fizzbuzz? %) "FizzBuzz")
                          #(when (fizz? %) "Fizz")
                          #(when (buzz? %) "Buzz"))]
    (or (to-words n) n)))

(defn fizzbuzz4
  ([n] (fizzbuzz4 n (array-map fizz? "Fizz" buzz? "Buzz")))
  ([n lookup] (if-let [matches (seq (keep (fn [[pred? word]] (when (pred? n) word)) lookup))]
                (apply str matches)
                n)))

; Simple patten matching using a single map lookup
; https://gist.github.com/walkermatt/08e971e28eb023a128ca
; Simple patten matching using a single map lookup
(defn fizzbuzz5 [x]
  (let [v [(= (mod x 3) 0) (= (mod x 5) 0)]]
    ({[true false] "Fizz"
      [false true] "Buzz"
      [true true]  "FizzBuzz"
      [false false] x} v)))

(defn -main
  "This code implements fizz-buzz in various ways"
  [& args])
