# set the name of the installer
Outfile "helloworld.exe"
!define TEMP_FILE "wherejava.tmp"

RequestExecutionLevel user

# create a default section
Section

    ;ExecWait 'cmd.exe /c "where java > ${TEMP_FILE}"'
    ;FileOpen $4 ${TEMP_FILE} r
    ;FileRead $4 $1
    ;FileClose $4
    ;Delete ${TEMP_FILE}
    ;StrCpy $0 $1 -14 ; bin/java.exe

    nsExec::ExecToStack  'cmd /c "where java"'
    Pop $0
    Pop $1
    StrCpy $0 $1 -14 ; cutting "bin/java.exe"

    # create a popup box, with an OK button and the text "Hello world!"
    MessageBox MB_OK "$0"

SectionEnd