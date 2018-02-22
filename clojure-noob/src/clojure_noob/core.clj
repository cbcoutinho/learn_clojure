(ns clojure-noob.core
  (:gen-class))

(defn factorial [n]
  (if (= n 0) 1
      (* n (factorial (dec n)))))

(defn factorial-bigint [n]
  (if (= n 0) 1
      ; the *'-prefix returns arbitrary precision numbers
      ; (clojure.lang.BigInt)
      (*' n (factorial-bigint (dec n)))))

; This will fail because recur is not in tail position
;(defn factorial-bigint-recur [n]
  ;(if (= n 0) 1
      ;(* n (recur (dec n)))))

(defn factorial-bigint-loop [n]
  (if (= n 0) 1
      (loop [val n i n]
        (if (<= i 1) val
          (recur (*' val (dec i)) (dec i))))))

(defn -main
  "I don't do a whole lot ... yet. But I will!"
  [& args]
  (factorial 25)
  (factorial-bigint 25)
  ;(factorial-bigint-recur 25000)
  (factorial-bigint-loop 2500))
