; Scheme standard library
; Taylor Lapeyre

(define (caar x) (car (car x)))
(define (cadr x) (car (cdr x)))
(define (cdar x) (cdr (car x)))
(define (cddr X) (cdr (cdr x)))

(define (caaar x) (car (car (car x))))
(define (caadr x) (car (car (cdr x))))
(define (cadar x) (car (cdr (car x))))
(define (caddr x) (car (cdr (cdr x))))
(define (cdaar x) (cdr (car (car x))))
(define (cddar x) (cdr (cdr (car x))))
(define (cdadr X) (cdr (car (cdr x))))
(define (cdddr x) (cdr (cdr (cdr x))))

(define (caaaar x) (car (car (car (car x)))))
(define (caaadr x) (car (car (car (cdr x)))))
(define (caadar x) (car (car (cdr (car x)))))
(define (caaddr X) (car (car (cdr (cdr x)))))
(define (cadaar x) (car (cdr (car (car x)))))
(define (caddar X) (car (cdr (cdr (car x)))))
(define (cadadr x) (car (cdr (car (cdr x)))))
(define (cadddr x) (car (cdr (cdr (cdr x)))))
(define (cdaaar x) (cdr (car (car (car x)))))
(define (cdaadr x) (cdr (car (car (cdr x)))))
(define (cdadar x) (cdr (car (cdr (car x)))))
(define (cdaddr x) (cdr (car (cdr (cdr x)))))
(define (cddaar x) (cdr (cdr (car (car x)))))
(define (cddadr x) (cdr (cdr (car (cdr x)))))
(define (cdddar x) (cdr (cdr (cdr (car x)))))
(define (cddddr x) (cdr (cdr (cdr (cdr x)))))

(define (reduce fn start coll)
  "Applies a function of two arguments to a list recursively until a value is
  returned."
  (if (null? coll)
    start
    (fn (car coll)
        (reduce fn start (cdr coll)))))

(define (standard-map fn coll)
  "Returns a new list that is the result of applying fn to each member
  of the given list."
  (if (null? coll)
    coll
    (cons (fn (car coll))
          (standard-map fn (cdr coll)))))

(define (map fn . lists)
  "Returns a new list that is the result of applying fn to the nth member
  of each of the given lists."
  (if (apply and (standard-map null? lists))
    '()
    (let ((cars (standard-map car lists))
          (cdrs (standard-map cdr lists)))
      (cons (apply fn cars)
            (apply map (cons fn cdrs))))))

(define (for-each f coll)
  "Applies the function to each member of the collection."
  (if (null? coll) coll
    (begin (f (car coll))
           (for-each f (cdr coll)))))

(define (filter pred coll)
  "Returns a new list that contains all members for which (pred item) is true."
  (if (null? coll)
    coll
    (if (pred (car coll))
      (cons (car coll)
            (filter pred (cdr coll)))
      (filter pred (cdr coll)))))

(define (append coll1 coll2)
  "Appends two lists together."
  (if (null? coll1)
    coll2
    (cons (car coll1)
          (append (cdr coll1) coll2))))

(define (reverse coll)
  "Reverses the collection."
  (if (null? coll) coll
     (append (reverse (cdr coll)) (list (car coll)))))

(define (length coll)
  "Returns the amount of members in a given list."
  (if (null? coll)
    0
    (b+ 1 (length (cdr coll)))))

(define (nth n coll)
  "Gets the nth member of a list."
  (if (zero? n)
    (car coll)
    (nth (b- n 1) (cdr coll))))

(define (nthcdr n coll)
  "Applies cdr to the list n + 1 number of times."
  (if (zero? n)
    (cdr coll)
    (nthcdr (b- n 1) (cdr coll))))

(define (> x y . more)
  "Returns non-nil if nums are in decreasing order, else false."
  (if (b> (length more) 0)
    (if (b> x y)
      (apply > (cons y more))
      #f)
    (b> x y)))

(define (< x y . more)
  "Returns non-nil if nums are in increasing order, else false."
  (if (b< (length more) 0)
    (if (b< x y)
      (apply < (cons y more))
      #f)
    (b< x y)))

(define (>= x y . more)
  (define (gte x y) (or (eq? x y) (b> x y)))
  "Returns non-nil if nums are in non-decreasing order, else false."
  (if (>= (length more) 0)
    (if (gte x y)
      (apply >= (cons y more))
      #f)
    (gte x y)))

(define (<= x y . more)
  (define (lte x y) (or (eq? x y) (b< x y)))
  "Returns non-nil if nums are in non-increasing order, else false."
  (if (<= (length more) 0)
    (if (lte x y)
      (apply <= (cons y more))
      #f)
    (lte x y)))

(define (= x y . more)
  "Numerical equality. Returns true if every argument is equal to each other."
  (if (b> (length more) 0)
    (if (b= x y)
      (apply = (cons y more))
      #f)
    (b= x y)))

(define (eqv? x y . more)
  (define (eqv x y)
    (cond ((and (string? x) (string? y)) (eq? x y))
          ((and (boolean? x) (boolean? y)) (eq? x y))
          ((and (symbol? x) (symbol? y)) (eq? x y))
          ((and (number? x) (number? y)) (= x y))
          ((and (null? x) (null? y)) #t)
          (else #f)))
  "Equality. Returns true if every argument is a primitive and is equal to
  each other"
  (if (b> (length more) 0)
    (if (eqv x y)
      (apply eqv? (cons y more))
      #f)
    (eqv x y)))

(define (equal? x y . more)
  (define (equal x y)
    (cond (and (pair? x) (pair? y))
            (and (equal? (car x) (car y))
                 (equal? (cdr x) (cdr y)))
          (else (eqv? x y))))
  "General equality. Returns true if every argument is equal to each other,
  including lists."
  (if (b> (length more) 0)
    (if (equal x y)
      (apply equal? (cons y more))
      #f)
    (equal x y)))

(define (positive? x)
  "Returns true if the number is greater than zero, else false."
  (b> x 0))

(define (negative? x)
  "Returns true if the number is less than zero, else false."
  (b< x 0))

(define (zero? x)
  "Returns true if the number is zero, else false."
  (b= x 0))

(define (compact coll)
  "Removes any members of the given list that evaluate to false."
  (filter (lambda (x) (if x x #f)) coll))

(define (not x)
  "Returns true if x is logical false, false otherwise."
  (if x #f #t))

(define (or . args)
  "Returns the first non-false value, or false."
  (let ((truthy-args (compact args)))
    (if (null? truthy-args)
      #f
      (car truthy-args))))

(define (and . args)
  "Returns the first falsey value given, or the last."
   (if (null? (cdr args)) (car args)
      (if (eq? (car args) #f)
        #f
        (apply and (cdr args)))))

(define (list . args)
  "Returns a new list containing the items."
  args)

(define (+ . args)
  "Returns the sum of args"
  (reduce b+ 0 args))

(define (* . args)
  "Returns the product of args."
  (reduce b* 1 args))

(define (- x . args)
  "If one argument is provided, returns the negation of x. Otherwise, returns
  the difference of args."
  (if (null? args)
    (b- (b* x 2) x)
    (reduce b- 0 (cons x args))))

(define (abs x)
  "Returns the absolute value of the given number."
  (cond ((b> x 0) x)
        ((b= x 0) 0)
        ((b< x 0) (b- x))))

(define (even? x)
  "Returns true if the argument is divisible by 2."
  (if (b= x 0) #t
    (if (b= (b- x 1) 0) #f
      (even? (b- x 2)))))

(define (odd? x)
  "Returns true if the argument is not even."
  (not (even? x)))

(define (max x . args)
  "Returns the largest of the args."
  (if (null? args)
    x
    (apply max (cons (if (b> x (car args)) x (car args))
                     (cdr args)))))

(define (min x . args)
  "Returns the smallest of the args."
  (if (null? args)
    x
    (apply min (cons (if (b< x (car args)) x (car args))
                     (cdr args)))))

(define (generic-member cmp obj lst)
  "Applies cmp to each member of the list, against obj. If the result
  is true, returns the list from that point on. Else, recur until false."
  (cond
    ((null? lst) #f)
    ((cmp obj (car lst)) lst)
    (else (generic-member cmp obj (cdr lst)))))

(define (memq obj lst)
  "Returns true if obj is a member of the given list. Uses the eq?
  comparison operator."
  (generic-member eq? obj lst))

(define (memv obj lst)
  "Returns true if obj is a member of the given list. Uses the eqv?
  comparison operator."
  (generic-member eqv? obj lst))

(define (member obj lst)
  "Returns true if obj is a member of the given list. Uses the equal?
  comparison operator."
  (generic-member equal? obj lst))

(define (generic-assoc cmp obj alst)
  "Applies cmp to each member of the list, against obj. If the result
  is true, returns the list from that point on. Else, recur until false."
  (cond
    ((null? alst) #f)
    ((cmp obj (caar alst)) (car alst))
    (else (generic-assoc cmp obj (cdr alst)))))

(define (assq obj alst)
  "Returns true if obj is the key to an associative pair in alst.
  Uses eq? as its comparison operator."
  (generic-assoc eq? obj alst))

(define (assv obj alst)
  "Returns true if obj is the key to an associative pair in alst.
  Uses eqv? as its comparison operator."
  (generic-assoc eqv? obj alst))

(define (assoc obj alst)
  "Returns true if obj is the key to an associative pair in alst.
  Uses equal? as its comparison operator."
  (generic-assoc equal? obj alst))