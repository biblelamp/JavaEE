; https://stackoverflow.com/questions/1659147/how-to-use-ghostscript-to-convert-pdf-to-pdf-a-or-pdf-x

; -dPDFA=1 -dPDFACompatibilityPolicy=1 -dNOOUTERSAVE -sProcessColorModel=DeviceRGB -sDEVICE=pdfwrite -o output_file.pdf /path/to/PDFA_def.ps input_file.pdf

; -dPDFA -dBATCH -dNOPAUSE -sColorConversionStrategy=UseDeviceIndependentColor -sDEVICE=pdfwrite -dPDFACompatibilityPolicy=3 -sOutputFile=output_filename.pdf input_filename.pdf

; -sDefaultRGBProfile=C:\Users\lamp\JavaEE\LightComp\pdfbox\src\main\resources\icc\sRGB2014.icc

; experimental command line

"C:\Program Files\Gs\bin\gswin64c.exe" -dPDFA=3 -sColorConversionStrategy=UseDeviceIndependentColor -sDEVICE=pdfwrite -o C:\temp\output.pdf C:\temp\input.pdf