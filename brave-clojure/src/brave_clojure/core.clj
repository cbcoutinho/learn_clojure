(ns brave-clojure.core)

(def asym-hobbit-body-parts
  [{:name "head"           :size 3}
   {:name "left-eye"       :size 1}
   {:name "left-ear"       :size 1}
   {:name "mouth"          :size 1}
   {:name "nose"           :size 1}
   {:name "neck"           :size 2}
   {:name "left-shoulder"  :size 3}
   {:name "left-upper-arm" :size 3}
   {:name "chest"          :size 10}
   {:name "back"           :size 10}
   {:name "left-forearm"   :size 3}
   {:name "abdomen"        :size 6}
   {:name "left-kidney"    :size 1}
   {:name "left-hand"      :size 2}
   {:name "left-knee"      :size 2}
   {:name "left-thigh"     :size 4}
   {:name "left-lower-leg" :size 3}
   {:name "left-achilles"  :size 1}
   {:name "left-foot"      :size 2}])

(defn matching-part
  "Does a quick `sed` replace for a body part based on name"
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expects of vector of maps that have a :name and a :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
          (into final-body-parts
            (set [part (matching-part part)])))))))

(defn better-symmetrize-body-parts
  "Expects of vector of maps that have a :name and a :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set [part (matching-part part)])))
          []
          asym-body-parts))

(=
  (symmetrize-body-parts asym-hobbit-body-parts)
  (better-symmetrize-body-parts asym-hobbit-body-parts))

(defn hit [asym-body-parts]
  "Hits a vector of maps where the target is based on a random number
  between 0 and (reduce + (map :size parts)). The size ratio of part
  size and total size is proportional to the probability of getting
  hit"
  (let [sym-parts (better-symmetrize-body-parts asym-body-parts)
        body-part-size-sum (reduce + (map :size sym-parts))
        target (rand body-part-size-sum)]
    ;(do
      ;(println target)
      (loop [[part & remaining] sym-parts
             accumulated-size (:size part)]
        (if (> accumulated-size target)
          part
          (recur remaining (+ accumulated-size (:size (first remaining))))))))

(hit asym-hobbit-body-parts)
