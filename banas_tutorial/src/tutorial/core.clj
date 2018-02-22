(ns tutorial.core
  (:require [clojure.string :as str])
  (:gen-class))

(use 'clojure.java.io)

; Macros
(defmacro discount
  [cond dis1 dis2]
  (list `if cond dis1 dis2))

(defmacro reg-math
  [calc]
  (list (second calc) (first calc) (nth calc 2)))

(defmacro do-more
  [cond & body]
  (list `if cond (cons `do body)))

(defmacro do-more-2
  [cond & body]
  `(if ~cond (do ~@body)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (discount (> 75 65) 
            (println "10% off") 
            (println "Full price"))

  (println (reg-math (2 + 5)))
  
  (do-more (< 1 2) 
    (println "hello")
    (println "Hello again"))

  (do-more-2 (< 1 2) 
    (println "hello")
    (println "Hello again")))
