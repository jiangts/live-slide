# Live Slides

Go ahead, Live Edit these slides!

---

# Many thanks to:
- http://platon.io/ who taught me this was possible
- [This source](https://github.com/juanpabloaj/platon/blob/master/public/app.js) that exposed
  the internal remark.js API to me
- The remark source, [slideshow.js](https://github.com/gnab/remark/blob/7e2aa4d313745997fa681c47f48639103183d93c/src/remark/models/slideshow.js) and [api.js](https://github.com/gnab/remark/blob/develop/src/remark/api.js)
- The beautiful, wonderful [SimpleMDE](https://github.com/NextStepWebs/simplemde-markdown-editor)   editor

---

# Some inspiration -- Markdown Editors

- [Top 10 markdown wysiwyg editors](http://www.developersfeed.com/awesome-javascript-wysiwyg-markdown-editors/)

Ones I kept open on my computer:
- https://stackedit.io/editor
- https://simplemde.com/
- https://pandao.github.io/editor.md/en.html
- https://markdown-it.github.io/

---

# Some inspiration -- Beautiful slideshows

- http://blog.hubspot.com/marketing/inspiring-slideshare-presentations-for-marketers-list#sm.0001evxlluh4zed4r231ay9feiura

---

# This codebase:

A [re-frame](https://github.com/Day8/re-frame) application designed to ... well, that part is up to you.

---

## Development Mode

### Compile css:

Compile css file once.

```
lein garden once
```

Automatically recompile css file on change.

```
lein garden auto
```

---

count: false

## Development Mode

### Run application:

```
lein clean
lein figwheel dev
```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

---

### Run tests:

```
lein clean
lein doo phantom test once
```

The above command assumes that you have [phantomjs](https://www.npmjs.com/package/phantomjs) installed. However, please note that [doo](https://github.com/bensu/doo) can be configured to run cljs.test in many other JS environments (chrome, ie, safari, opera, slimer, node, rhino, or nashorn).

---

## Production Build


To compile clojurescript to javascript:

```
lein clean
lein cljsbuild once min
```
