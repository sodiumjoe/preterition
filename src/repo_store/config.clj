(ns repo-store.config)

(def path-prefix "./repos/")

(def ^:private raw-configs [{:username "joebadmo"
                             :repository "joe.xoxomoon.com-content"
                             :branch "repo-store"}])

(defn- enrich [config]
  (let [repo (str (config :username) "/" (config :repository))
        path (str path-prefix repo)]
    (assoc config :repo repo :path path)))

(defn- index-by [k config] {(config k) config})

(index-by :repo (enrich (first raw-configs)))

(def configs (->> (map enrich raw-configs)
                            (map (partial index-by :repo))
                            (reduce #(merge %1 %2) {})))
