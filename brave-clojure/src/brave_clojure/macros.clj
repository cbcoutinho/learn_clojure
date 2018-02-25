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
