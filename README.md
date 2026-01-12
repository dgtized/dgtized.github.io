# dgtized.github.io

Personal webpage, using [babashka](https://babashka.org/) and
[hiccup](https://github.com/weavejester/hiccup) for static site generation.

# Usage

[install babashka](https://github.com/babashka/babashka#installation) and run

```
bb build # generates pages in 'static' subdirectory
```

# Photos

Add photos to `resources/public/photos`, preferably resizing with 

```
magick $input -size 1024x768 resources/public/photos/$output
```

Note that only the images listed in `src/static.clj` are currently copying over.

# Video

Rescale with:

```
ffmpeg -i $input -vf "scale=-2:720" resources/public/video/$output
```
