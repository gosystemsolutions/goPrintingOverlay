# goPrintingOverlay

A photobooth for events!

Prints a JPEG with a border on 4x6 inch photo paper!

## Usage

```sh
java -jar goprintingoverlay-1.0-SNAPSHOT-all.jar "C:\Location\To\The\Image.jpg"
```

## Top Tips

Set your Canon EOS Utility 2 "Linked Software" to a batch file with the following:

```bat
java -jar "C:\Location\To\The\goprintingoverlay-1.0-SNAPSHOT-all.jar" %*
```

This makes sure that the Jar is loaded automatically after a photograph is taken.

## Licence

This project is for use for goSystem only at events.
