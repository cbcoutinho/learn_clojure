(ns brave-clojure.vampire)

(def vampire-database
  {0 {:makes-blood-puns? false, :has-pulse? true,  :name "McFishwich"}
   1 {:makes-blood-puns? false, :has-pulse? true,  :name "McMackson"}
   2 {:makes-blood-puns? true,  :has-pulse? false, :name "Damon Salvatore"}
   3 {:makes-blood-puns? true,  :has-pulse? true,  :name "Mickey Mouse"}})

(defn vampire-related-details
  [social-security-number]
  (Thread/sleep 1000)
  (get vampire-database social-security-number))

(defn vampire?
  [record]
  (and (:makes-blood-puns? record)
       (not (:has-pulse? record))
       record))

(defn identify-vampire
  [social-security-numbers]
  (first (filter vampire?
                 (map vampire-related-details social-security-numbers))))

; Non-lazy version of testing
(time (vampire-related-details 0))

; Initializing a `map` is Lazy!
(time (def mapped-details (map vampire-related-details (range 0 1000000))))

; Using a lazy `map` incurs the actual cost (only the first time):
(time (first mapped-details))

; For some reason the following result isn't cached - takes 32s
; every time.
(time (identify-vampire (range 0 1000000)))
