(ns brave-clojure.macros
  (:require [clojure.string :refer [upper-case]]))

;; Ch 7: Clojure Alchemy: Reading, Evaluation, and Macros

(defmacro backwards
  [form]
  (reverse form))

; Trivial usage of a macro
(backwards (" backwards" " am" "I" str))

(str "To understand what recursion is," " you must first understand recursion.")

(read-string "'(a b c)'")

(defn exclaim
  [exclamation]
  (str (upper-case exclamation) "!"))

(exclaim "Hadoken")
; => "HADOKEN!"

;(eval (read-string "(1 + 1)"))
; => Error!

(let [infix (read-string "(1 + 1)")]
  (list (second infix) (first infix) (last infix)))
; => (+ 1 1)

(eval 
  (let [infix (read-string "(1 + 1)")]
    (list (second infix) (first infix) (last infix))))
; => 2

(defmacro ignore-last-operand
  [form]
  (butlast form))

(macroexpand '(ignore-last-operand (+ 1 2 10)))

; Use a macro to make (a + b) => (+ a b)
(defmacro infix
  [form]
  (list (second form)
        (first form)
        (last form)))

(infix (1 + 2))


; With the threading/`stabby` macro, you can pipe input from one
; function to another. The following two functions are identical
(defn read-resource
  "Read a resource into a string"
  [path]
  (read-string (slurp (clojure.java.io/resource path))))

(defn read-resource
  [path]
  (-> path
      clojure.java.io/resource
      slurp
      read-string))

(macroexpand '(when (the-cows-come :home)
                (call me :pappy)
                (slap me :silly)))
; => (if (the-cows-come :home)
;      (do (call me :pappy)
;          (slap me :silly))


;; Two ways to write a `code-critic` macro:
; Quoting each function individually
(defmacro code-critic1
  "Phrases are courtesy of Hermes Conrad from Futurama"
  [bad good]
  (list 'do
        (list 'println
              "Great squid of Madrid, this is bad code:"
              (list 'quote bad))
        (list 'println
              "Sweet gorilla of Manila, this is good code:"
              (list 'quote good))))

; Using syntax quoting to 'quote by default', and using `~` to negate
; the syntax quoting
(defmacro code-critic2
  "Phrases are courtesy of Hermes Conrad from Futurama"
  [bad good]
  `(do (println "Great squid of Madrid, this is bad code:"
                (quote ~bad))
       (println "Sweet gorilla of Manila, this is good code:"
                (quote ~good))))

(code-critic1 (1 + 1) (+ 1 1))
(code-critic2 (1 + 1) (+ 1 1))
