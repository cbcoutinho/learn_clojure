(ns insane-noises.core
  (:require [overtone.live :refer :all])
  (:require [overtone.inst.drum :as drum]))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

; To play, execute (demo1)
(defn demo1 []
  (demo (sin-osc)))

; To play, execute (demo2)
(defn demo2 []
  (demo 7 (lpf (mix (saw [50 (line 100 1600 5) 101 100.5]))
               (lin-lin (lf-tri (line 2 20 5)) -1 1 400 4000))))

; Play a simple drum kick
(drum/kick)

; Define instance of `saw`
;   see doc for more (odoc saw)
(definst foo [freq 440] (* 0.3 (saw freq)))
(foo 220)
(foo 660)
(foo)

(kill foo)  ; Stop all instances of `foo`
(stop)      ; Kill everything


;; Use `ctl` to control ugens
(definst quux [freq 440] 
  (* 0.3 (saw freq)))
(quux)

; Use `ctl` to control parameters of ugens
(ctl quux :freq 260)


(definst trem [freq 440 depth 10 rate 6 length 3]
  (* 0.3
     (line:kr 0 1 length FREE)
     (saw (+ freq (* depth (sin-osc:kr rate))))))

(trem)
(trem 200 60 0.8)
(trem 60 30 0.2)
