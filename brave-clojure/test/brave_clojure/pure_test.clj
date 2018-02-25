(ns brave-clojure.pure-test
  (:require [clojure.test :refer :all]
            [brave-clojure.pure :refer :all]))

; character:
; {:name "Smooches McCutes"
;  :attributes {:intelligence 10
;               :strength      4
;               :dexterity     5}}

(deftest comp-test
  (testing "Compositions of functions"
    (is (and
          (= (c-int character) 10)
          (= (c-str character)  4)
          (= (c-dex character)  5)))))

(deftest ex1-test
  (testing "Excerise 1 from brave-clojure Ch 5:
           You used (comp :intelligence :attributes) 
           to create a function that returns a character’s 
           intelligence. Create a new function, attr, that you can 
           call like (attr :intelligence) and that does the 
           same thing."
    (is (and
          (= ((attr :intelligence) character)
             (c-int character))
          (= ((attr :strength) character)
             (c-str character))
          (= ((attr :dexterity) character)
             (c-dex character))))))
