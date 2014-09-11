(define (count-occurances the-list atom)
  (if (equal? the-list '()) 0
    (+ (if (eqv? (car the-list) atom) 1 0)
       (count-occurances
         ((if (list? (car the-list)) car cdr) the-list)
         atom))))

(define a-list '(1 2 3 4 5 5 6 5))

(display (count-occurances a-list 5))
(newline)
