(ns tutorial.core
  (:require [clojure.string :as str])
  (:gen-class))

; Math
(defn math-stuff
  []
  (println (+ 1 2 3))          ;; Add values together
  (println (- 5 3 2))          ;; Subtract values
  (println (* 2 5))            ;; Multiply Values
  (println (/ 10 5))           ;; Divide Values
  (println (mod 12 5))         ;; Modulus
  (println (inc 5))            ;; Increment
  (println (dec 5))            ;; Decrement

  (println (Math/abs -10))     ;; Absolute Value
  (println (Math/cbrt 8))      ;; Cube Root
  (println (Math/sqrt 4))      ;; Square Root
  (println (Math/ceil 4.5))    ;; Round up
  (println (Math/floor 4.5))   ;; Round down
  (println (Math/exp 1))       ;; e to the power of 1
  (println (Math/hypot 2 2))   ;; sqrt(x^2 + y^2)
  (println (Math/log 2.71828)) ;; Natural logarithm
  (println (Math/log10 100))   ;; Base 10 log
  (println (Math/max 1 5))
  (println (Math/min 1 5))
  (println (Math/pow 2 2)))    ;; Power

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (math-stuff))

