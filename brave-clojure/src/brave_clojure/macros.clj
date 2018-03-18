(ns brave-clojure.macros
  (:require [clojure.string :refer [upper-case]]))

;; Ch 7: Clojure Alchemy: Reading, Evaluation, and Macros

(defmacro backwards
  [form]
  (reverse form))


(defn exclaim
  [exclamation]
  (str (upper-case exclamation) "!"))

(exclaim "Hadoken")
; => "HADOKEN!"

;(eval (read-string "(1 + 1)"))
; => Error!

;(let [infix (read-string "(1 + 1)")]
  ;(list (second infix) (first infix) (last infix)))
; => (+ 1 1)

;(eval
  ;(let [infix (read-string "(1 + 1)")]
    ;(list (second infix) (first infix) (last infix))))
; => 2

(defmacro ignore-last-operand
  [form]
  (butlast form))

; Use a macro to make (a + b) => (+ a b)
(defmacro infix
  [form]
  (list (second form)
        (first form)
        (last form)))


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

; Clean up the syntax quoting example #2 with the following:
(defn criticize-code
  "This function returns a syntax-quoted list"
  [criticism code]
  `(println ~criticism (quote ~code)))

(defmacro code-critic3
  [bad good]
  `(do ~(criticize-code "Cursed bacteria of Liberia, this is bad code:" bad)
       ~(criticize-code "Sweet sacred boa of Western and Eastern Samoa, this is good code:" good)))

(code-critic3 (1 + 1) (+ 1 1))

; Use map instead of a call to `do`. Shows how to use `unquote
; splicing`, which unwraps a `seq`able data structure
(defmacro code-critic4
  "Phrases are courtesy of Hermes Conrad from Futurama"
  [bad good]
  `(do ~@(map #(apply criticize-code %)
              [["Sweet lion of Zion, this is bad code:" bad]
               ["Great cow of Moscow, this is good code:" good]])))

(code-critic4 (1 + 1) (+ 1 1))

; The `gensym` and `auto gensym` constructs keep results of forms for
; later use within a macro. This the `report` macro, the result of
; `to-try` is saved in result#, and used in the if statement below.
; This is different that using executing ~to-try within the macro
; itself. NOTE: The (quote ~to-try) form does not execute `to-try`.
(defmacro report
  [to-try]
  `(let [result# ~to-try]
     (if result#
       (println (quote ~to-try) "was successful:" result#)
       (println (quote ~to-try) "was not successful:" result#))))

;  $ (source and)
;  (defmacro and
;    "Evaluates exprs one at a time, from left to right. If a form
;    returns logical false (nil or false), and returns that value and
;    doesn't evaluate any of the other expressions, otherwise it returns
;    the value of the last expr. (and) returns true."
;    {:added "1.0"}
;    ([] true)
;    ([x] x)
;    ([x & next]
;     `(let [and# ~x]
;        (if and# (and ~@next) and#))))

(defmacro my-or
  "Evaluates exprs one at a time, from left to right. If a form
  returns a logical true value, or returns that value and doesn't
  evaluate any of the other expressions, otherwise it returns the
  value of the last expression. (or) returns nil."
  ([] nil)
  ([x] x)
  ([x & next]
   `(let [my-or# ~x]
      (if my-or# my-or# (my-or ~@next)))))
