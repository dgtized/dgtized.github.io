(ns bb.pages.portfolio
  (:require [hiccup.element :as he]))

(comment {:title (he/link-to "" "")
          :desc ""})

(def art-exploration
  [{:title [:span (he/link-to "https://dgtized.github.io/shimmers" "Shimmers")
            " "
            (he/link-to "https://github.com/dgtized/shimmers" "(code)")]
    :desc "A digital sketchbook of generative art"}
   {:title [:span (he/link-to "https://dgtized.github.io/lemniscate" "Lemniscate")
            ;; (he/link-to "" "")
            ]
    :desc "A simple language, editor, compiler, and firmware for visualizing and live programming an LED sculpture"}
   {:title [:span (he/link-to "https://dgtized.github.io/autostereogram" "Autostereogram")
            " "
            (he/link-to "https://github.com/dgtized/autostereogram" "(code)")]
    :desc [:span
           "Generate "
           (he/link-to "https://en.wikipedia.org/wiki/Autostereogram" "autostereograms")
           " from a depth-map and a pattern."]}
   {:title (he/link-to "https://github.com/dgtized/advent-of-code" "Advent of Code")
    :desc "Polyglot solutions to practice various programming language paradigms"}
   {:title (he/link-to "https://github.com/joneshf/forth-to-forth" "Forth to Forth")
    :desc "An effort to learn both Go and Forth by writing parts of a Forth interpreter in Go"}
   {:title [:span (he/link-to "https://dgtized.github.io/sierpinski-sieve" "Sierpinski Sieve")
            " "
            (he/link-to "https://github.com/dgtized/sierpinski-sieve" "(code)")]
    :desc "Visualize Sierpinsky triangles"}
   {:title [:span (he/link-to "https://dgtized.github.io/tile-game" "NxN Tile-game Solver")
            " "
            (he/link-to "https://github.com/dgtized/tile-game" "(code)")]
    :desc [:span "A "
           (he/link-to "https://en.wikipedia.org/wiki/Sliding_puzzle" "sliding puzzle")
           " game with a partial solver"]}])

(def tooling
  [{:title (he/link-to "https://github.com/dgtized/stack-mitosis"
                       "stack-mitosis")
    :desc "Synchronize production/staging environments by cloning & replacing AWS-RDS replication graphs"}
   {:title (he/link-to "https://github.com/dgtized/time-butler"
                       "time-butler")
    :desc "Cross-build CI monitoring for identifying performance bottlenecks and non-deterministic tests"}
   {:title (he/link-to "https://github.com/dgtized/scrapyard"
                       "scrapyard")
    :desc "Simple caching tool for faster CI builds"}
   {:title (he/link-to "https://github.com/dgtized/deploy-complexity"
                       "deploy-complexity")
    :desc "Analyze git history to report complexity of changes in each deploy"}
   {:title (he/link-to "https://github.com/dgtized/lein-vanity"
                       "lein-vanity")
    :desc "lines of code for vanity's sake"}])

(def emacs-packages
  [{:title (he/link-to "https://github.com/dgtized/winnow.el"
                       "winnow.el")
    :desc "Winnow search results from ag/grep by matching/excluding lines"}
   {:title (he/link-to "https://github.com/dgtized/occur-context-resize.el"
                       "occur-context-resize.el")
    :desc "Dynamically resize context around matches in occur-mode"}
   {:title (he/link-to "https://github.com/dgtized/github-clone.el"
                       "github-clone.el")
    :desc "Fork and clone Github projects from Emacs"}
   {:title (he/link-to "https://github.com/dgtized/list-environment.el"
                       "list-environment.el")
    :desc "A tabulated process environment editor"}
   {:title (he/link-to "https://github.com/dgtized/brave-new-emacs"
                       "brave-new-emacs")
    :desc "Self executing Org-mode presentation on Emacs initialization with packages"}
   {:title (he/link-to "https://github.com/dgtized/dotfiles" "dotfiles")
    :desc "My development environment (mostly emacs configuration)"}])

(defn group [category projects]
  [:section
   [:h3 category]
   [:ul
    (for [{:keys [title desc]} projects]
      [:li title " - " desc])]])

(defn section []
  [:section
   [:h2 "Portfolio"]
   (group "Art & Exploration" art-exploration)
   (group "Developer Tooling" tooling)
   (group "Emacs Packages" emacs-packages)])
