(define (assert pred)
  (if pred
    (begin
      (display "PASSED ")
      (newline))
    (begin
      (display "FAILED ")
      (newline))))

(display "testing and\n")
(assert (= (and 1 2 3 4) 4))
(newline)

(define (test)
  (assert
    (and
      (display "testing not\n")
      (not #f)

      (display "testing =\n")
      (= 1 1 1 1)

      (display "testing +\n")
      (= (+ 1 2 3) 6)

      (display "testing -\n")
      (= (- 7 2) 5)

      (display "testing *\n")
      (= (* 10 2) 20)

      (display "testing >\n")
      (> 3 2 1)
      ;(not (> 3 3 2 1))  (display "testing Failing!\n")

      (display "testing <\n")
      (< 1 2 3)
      ;(not (< 1 2 3 3))  (display "testing Failing!\n")

      (display "testing zero?\n")
      (zero? 0)
      (not (zero? 1))

      (display "testing positive?\n")
      (positive? 1)
      (not (positive? -1))

      (display "testing negative?\n")
      (negative? -1)
      (not (negative? 1))

      (display "testing odd?\n")
      (odd? 3)
      (not (odd? 2))

      (display "testing even?\n")
      (even? 4)
      (not (even? 3))

      (display "testing max\n")
      (= (max 1 2 3 4) 4)

      (display "testing min\n")
      (= (min 1 2 3 4) 1)

      (display "testing or\n")
      (= (or #f #f 4 #f) 4)
      (not (or #f #f #f))

      (display "testing length\n")
      (= (length '(1 2 3 4 5)) 5)

      (display "testing eqv?\n")
      (eqv? "hello" "hello") 

      (display "testing equal?\n")
      (equal? '(1 2 3) '(1 2 3))

      (display "testing append\n")
      (equal? (append '(1 2 3) '(4 5 6)) '(1 2 3 4 5 6))

      (display "testing reverse\n")
      (equal? (reverse '(1 2 3)) '(3 2 1))

      (display "testing memq\n")
      (equal? (memq 2 '(1 2 3 4)) '(2 3 4))

      (display "testing memv\n")
      (equal? (memv 2 '(1 2 3 4)) '(2 3 4))

      (display "testing member\n")
      (equal? (member '(a) '(b (a) c)) '((a) c))

      (display "testing assq\n")
      (equal? (assq 'a '((a 1) (b 2) (c 3))) '(a 1))
      (not (assq (list 'a) '(((a)) ((b)) ((c)))))

      (display "testing assv\n")
      (equal? (assv 5 '((2 3) (5 7) (11 13))) '(5 7))

      (display "testing assoc\n")
      (equal? (assoc (list 'a) '(((a)) ((b)) ((c)))) '((a)))

      (display "testing simple map\n")
      (equal? (map (lambda (x) (+ x 1)) '(1 2 3)) '(2 3 4))

      (display "testing complex map\n")
      (equal? (map + '(1 2 3) '(4 5 6) '(7 8 9)) '(12 15 18))

      (display "testing for-each\n")
      (for-each display '(1 2 3))
      (newline)

)))

(test)
