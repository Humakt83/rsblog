(ns rsblog.admin)

(def admin-login (or (System/getenv "RSBLOG_ADMIN_LOGIN")
                     "admin"))
(def admin-passw (or (System/getenv "RSBLOG_ADMIN_PASSW")
                     "admin"))

(defn check-login [login passw]
  (and (= login admin-login)
       (= passw admin-passw)))