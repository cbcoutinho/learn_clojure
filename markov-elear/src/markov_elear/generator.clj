(ns markov-elear.generator)

(def example "And the Golden Grouse And the Pobble who")
;; -> #'markov-elear.core/example

(def words (clojure.string/split example #" "))
;; words
;; -> ["And" "the" "Golden" "Grouse" "And" "the" "Pobble" "who"]

(def word-transitions (partition-all 3 1 words))
;; word-transitions
;; -> (("And" "the" "Golden")
;;     ("the" "Golden" "Grouse")
;;     ("Golden" "Grouse" "And")
;;     ("Grouse" "And" "the")
;;     ("And" "the" "Pobble")
;;     ("the" "Pobble" "who")
;;     ("Pobble" "who")
;;     ("who"))

(defn word-chain [word-transitions]
  (reduce (fn [r t] (merge-with clojure.set/union r
                                (let [[a b c] t] 
                                  {[a b] (if c #{c} #{})})))
          {}
          word-transitions))

;; -> {["who" nil] #{},
;;     ["Pobble" "who"] #{}, 
;;     ["the" "Pobble"] #{"who"}, 
;;     ["Grouse" "And"] #{"the"}, 
;;     ["Golden" "Grouse"] #{"And"}, 
;;     ["the" "Golden"] #{"Grouse"}, 
;;     ["And" "the"] #{"Pobble" "Golden"}} 

(defn text->word-chain [s]
  (let [words (clojure.string/split s #"[\s|\n]")
        word-transitions (partition-all 3 1 words)]
    (word-chain word-transitions)))
