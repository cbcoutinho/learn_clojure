(ns brave-clojure.macros_test
  (:require [clojure.test :refer :all :exclude [report]]
            [brave-clojure.macros :refer :all]))

(deftest backwards-test
  (testing "Trivial usage of macro"
    (is (=
          "I am backwards"
          (backwards (" backwards" " am" "I" str))))))

(deftest infix-test
  (testing "Convert infix notation to lisp form notation"
    (is (= 2
           (eval (let [infix (read-string "(1 + 1)")]
                   (list (second infix) (first infix) (last infix))))))))

; Tests the following: (macroexpand '(ignore-last-operand (+ 1 2 10)))
(deftest ignore-last-test
  (testing "Ignore last operand"
    (is (= (+ 1 2)
           (ignore-last-operand (+ 1 2 3))))))

; Compare different code-critic methods
(deftest code-critic-test
  (testing "Compares various code-critic implementations"
    (is (=
          (code-critic1 (1 + 1) (+ 1 1))
          (code-critic2 (1 + 1) (+ 1 1))))))

;(code-critic3 (1 + 1) (+ 1 1))
;(code-critic4 (1 + 1) (+ 1 1))

; Re-write `or` as a macro
(deftest my-or-test
  (testing "Compares `or` to `my-or`"
    (is (and
          (=
            (or 3 5)
            (my-or 3 5))
          (=
            (or nil 10)
            (my-or nil 10))))))
