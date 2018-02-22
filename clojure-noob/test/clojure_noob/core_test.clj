(ns clojure-noob.core-test
  (:require [clojure.test :refer :all]
            [clojure-noob.core :refer :all]))

;(deftest a-test
  ;(testing "FIXME, I fail."
    ;(is (= 0 1))))

(def test-string '(1, 2, "Fizz", 4, "Buzz", "Fizz", 7, 8, "Fizz", "Buzz", 11, "Fizz", 13, 14, "FizzBuzz", 16, 17, "Fizz", 19, "Buzz", "Fizz", 22, 23, "Fizz", "Buzz", 26, "Fizz", 28, 29, "FizzBuzz", 31, 32, "Fizz", 34, "Buzz", "Fizz", 37, 38, "Fizz", "Buzz", 41, "Fizz", 43, 44, "FizzBuzz", 46, 47, "Fizz", 49, "Buzz", "Fizz", 52, 53, "Fizz", "Buzz", 56, "Fizz", 58, 59, "FizzBuzz", 61, 62, "Fizz", 64, "Buzz", "Fizz", 67, 68, "Fizz", "Buzz", 71, "Fizz", 73, 74, "FizzBuzz", 76, 77, "Fizz", 79, "Buzz", "Fizz", 82, 83, "Fizz", "Buzz", 86, "Fizz", 88, 89, "FizzBuzz", 91, 92, "Fizz", 94, "Buzz", "Fizz", 97, 98, "Fizz", "Buzz"))

(deftest fizzbuzz1-test
  (testing "fizzbuzz1"
    (is (= (map fizzbuzz1 (range 1 101)) test-string))))

(deftest fizzbuzz2-test
  (testing "fizzbuzz2"
    (is (= (map fizzbuzz2 (range 1 101)) test-string))))

(deftest fizzbuzz3-test
  (testing "fizzbuzz3"
    (is (= (map fizzbuzz3 (range 1 101)) test-string))))

(deftest fizzbuzz4-test
  (testing "fizzbuzz4"
    (is (= (map fizzbuzz4 (range 1 101)) test-string))))

(deftest fizzbuzz5-test
  (testing "fizzbuzz5"
    (is (= (map fizzbuzz5 (range 1 101)) test-string))))

