!define SOURCE_DIR   ".\iTrivio\"
!define ITRIVIO_DIR  "\itrivio\"
!define IROOT_DIR    "\webapps\ROOT"
!define ITRIVIO_DATA "\itrivio_data"
!define JDK8_EXE     "jdk-8u201-windows-x64.exe"
!define MYSQL_MSI    "mariadb-5.5.63-winx64.msi"
!define LOFFICE_MSI  "LibreOffice_6.1.4_Win_x64.msi"
!define PSPAD_EXE    "pspad501inst_cz.exe"
!define FREECOMM_MSI "FreeCommanderXE-32-public_790.msi"
!define JETTY_DIR    "jetty-distribution-9.4.14"
!define JBASEIN_BAT  "jettyBaseInstall.bat"  ; replace: JETTYDIR
!define JSTART_BAT   "start.bat"             ; replace: JETTYDIR
!define INSTS_BAT    "install_service.bat"   ; replace: INSTDIR, JETTYDIR, JDKHOME
!define UNINSTS_BAT  "uninstall_service.bat" ; replace: INSTDIR, JETTYDIR
!define COMMONS_DIR  "commons-daemon"
!define ITRIVIO_WAR  "ROOT_itrivio.war"
!define ISETPROP_DIR "\WEB-INF\classes\"
!define ISPROP_FILE  "settings.properties"   ; replace: INSTDIR
!define DBPROP_FILE  "database.properties"   ; replace: DHOST, DPORT, DNAME, DUSER, UPSWD

;--------------------------------
;Include header files

  !include StrRep.nsh           ; replacing in string
  !include ReplaceInFile.nsh    ; replacing in file(s)
  !include nsDialogs.nsh        ; creating dialogs

;--------------------------------
;Include Modern UI

  !include "MUI2.nsh"

;--------------------------------
;General

  ;Name and file
  Name "Educasoft iTrivio installer"
  OutFile "iTrivio.exe"

  ;Request application privileges for Windows
  RequestExecutionLevel admin

  ;Allow file overwriting
  SetOverWrite try

;--------------------------------
;Pages

  !insertmacro MUI_PAGE_WELCOME
  !insertmacro MUI_PAGE_COMPONENTS
  !insertmacro MUI_PAGE_DIRECTORY
  Page Custom nsDialogsPage nsDialogExitPage
  !insertmacro MUI_PAGE_INSTFILES
  !insertmacro MUI_PAGE_FINISH

;--------------------------------
;Languages

  !insertmacro MUI_LANGUAGE "English"

;--------------------------------
;Variables for nsDialogs

Var DHOST   ; host of database
Var DPORT   ; port
Var DNAME   ; name of database
Var DUSER   ; user name
Var DPSWD   ; password

;--------------------------------
;Functions

Function .onInit    ; predefined function

    StrCpy $INSTDIR $PROFILE
    StrCpy $DHOST "jdbc:mysql://127.0.0.1"
    StrCpy $DPORT "3306"
    StrCpy $DNAME "itrivio"
    StrCpy $DUSER "root"
    StrCpy $DPSWD "root"

FunctionEnd

Function nsDialogsPage

    nsDialogs::Create 1018
    Pop $0

    ${NSD_CreateLabel} 0 0 100% 12u "Host of database"
    Pop $0

    ${NSD_CreateText} 0 12u 100% 12u $DHOST
    Pop $1

    ${NSD_CreateLabel} 0 24u 100% 12u "Port of database"
    Pop $0

    ${NSD_CreateText} 0 36u 100% 12u $DPORT
    Pop $2

    ${NSD_CreateLabel} 0 48u 100% 12u "Name of database"
    Pop $0

    ${NSD_CreateText} 0 60u 100% 12u $DNAME
    Pop $3

    ${NSD_CreateLabel} 0 72u 100% 12u "Database username"
    Pop $0

    ${NSD_CreateText} 0 84u 100% 12u $DUSER
    Pop $4

    ${NSD_CreateLabel} 0 96u 100% 12u "Database password"
    Pop $0

    ${NSD_CreateText} 0 108u 100% 12u $DPSWD
    Pop $5

    nsDialogs::Show

FunctionEnd

Function nsDialogExitPage

    ${NSD_GetText} $1 $DHOST
    ${NSD_GetText} $2 $DPORT
    ${NSD_GetText} $3 $DNAME
    ${NSD_GetText} $4 $DUSER
    ${NSD_GetText} $5 $DPSWD

FunctionEnd

;--------------------------------
;Installer Sections

Section "${JDK8_EXE}" JDK8

    SetOutPath "$TEMP"
    ;File "${SOURCE_DIR}${JDK8_EXE}"
    ExecWait "$TEMP\${JDK8_EXE}"

    Delete "$TEMP\${JDK8_EXE}"

SectionEnd

Section "${MYSQL_MSI}" MySQL

    SetOutPath "$TEMP"
    ;File "${SOURCE_DIR}${MYSQL_MSI}"
    ExecWait "msiexec /i $TEMP\${MYSQL_MSI}"

    Delete "$TEMP\${MYSQL_MSI}"

SectionEnd

Section "${LOFFICE_MSI}" LibreOffice

    SetOutPath "$TEMP"
    ;File "${SOURCE_DIR}${LOFFICE_MSI}"
    ExecWait "msiexec /i $TEMP\${LOFFICE_MSI}"

    Delete "$TEMP\${LOFFICE_MSI}"

SectionEnd

Section "${PSPAD_EXE}" PSPad

    SetOutPath "$TEMP"
    ;File "${SOURCE_DIR}${PSPAD_EXE}"
    ExecWait "$TEMP\${PSPAD_EXE}"

    Delete "$TEMP\${PSPAD_EXE}"

SectionEnd

Section "${FREECOMM_MSI}" FreeCommander

    SetOutPath "$TEMP"
    ;File "${SOURCE_DIR}${FREECOMM_MSI}"
    ExecWait "msiexec /i $TEMP\${FREECOMM_MSI}"

    Delete "$TEMP\${FREECOMM_MSI}"

SectionEnd

Section "${JETTY_DIR}" Jetty

    SetOutPath "$TEMP"
    File "${SOURCE_DIR}${JETTY_DIR}.zip"

    SetOutPath "$INSTDIR\${COMMONS_DIR}"
    File "${SOURCE_DIR}${COMMONS_DIR}\*"

    ZipDLL::extractall "$TEMP\${JETTY_DIR}.zip" $INSTDIR

    Delete "$TEMP\${JETTY_DIR}.zip"

    SetOutPath "$INSTDIR${ITRIVIO_DIR}"
    File "${SOURCE_DIR}${JBASEIN_BAT}"

    !insertmacro ReplaceInFile "$INSTDIR${ITRIVIO_DIR}${JBASEIN_BAT}" "{JETTYDIR}" ${JETTY_DIR}

    ExpandEnvStrings $0 %COMSPEC%
    ExecWait '"$0" /C "$INSTDIR${ITRIVIO_DIR}${JBASEIN_BAT}"'

    CreateDirectory $INSTDIR${ITRIVIO_DATA}

    SetOutPath "$INSTDIR${ITRIVIO_DIR}"
    File "${SOURCE_DIR}${JSTART_BAT}"

    !insertmacro ReplaceInFile "$INSTDIR${ITRIVIO_DIR}${JSTART_BAT}" "{JETTYDIR}" ${JETTY_DIR}

    # getting path to JDK
    nsExec::ExecToStack 'cmd /c "where java"'
    Pop $0
    Pop $1
    StrCpy $0 $1 -15 ; cutting "\bin\java.exe"

    SetOutPath "$INSTDIR"
    File "${SOURCE_DIR}${INSTS_BAT}"
    File "${SOURCE_DIR}${UNINSTS_BAT}"

    # replacements in install_service.bat
    !insertmacro ReplaceInFile "$INSTDIR\${INSTS_BAT}" "{INSTDIR}" $INSTDIR
    !insertmacro ReplaceInFile "$INSTDIR\${INSTS_BAT}" "{JETTYDIR}" ${JETTY_DIR}
    !insertmacro ReplaceInFile "$INSTDIR\${INSTS_BAT}" "{JDKHOME}" $0

    # replacements in uninstall_service.bat
    !insertmacro ReplaceInFile "$INSTDIR\${UNINSTS_BAT}" "{INSTDIR}" $INSTDIR
    !insertmacro ReplaceInFile "$INSTDIR\${UNINSTS_BAT}" "{JETTYDIR}" ${JETTY_DIR}

SectionEnd

Section "${ITRIVIO_WAR}" iTrivio.war

    SetOutPath "$TEMP"
    ;File "${SOURCE_DIR}${ITRIVIO_WAR}"

    ZipDLL::extractall "$TEMP\${ITRIVIO_WAR}" "$INSTDIR\${ITRIVIO_DIR}\${IROOT_DIR}"

    Delete "$TEMP\${ITRIVIO_WAR}"

    # replace in settings.properties
    !insertmacro StrRep $1 $INSTDIR "\" "/"
    !insertmacro ReplaceInFile "$INSTDIR${ITRIVIO_DIR}${IROOT_DIR}${ISETPROP_DIR}${ISPROP_FILE}" "{INSTDIR}" $1

    # replace in database.properties
    !insertmacro ReplaceInFile "$INSTDIR${ITRIVIO_DIR}${IROOT_DIR}${ISETPROP_DIR}${DBPROP_FILE}" "{DHOST}" $DHOST
    !insertmacro ReplaceInFile "$INSTDIR${ITRIVIO_DIR}${IROOT_DIR}${ISETPROP_DIR}${DBPROP_FILE}" "{DPORT}" $DPORT
    !insertmacro ReplaceInFile "$INSTDIR${ITRIVIO_DIR}${IROOT_DIR}${ISETPROP_DIR}${DBPROP_FILE}" "{DNAME}" $DNAME
    !insertmacro ReplaceInFile "$INSTDIR${ITRIVIO_DIR}${IROOT_DIR}${ISETPROP_DIR}${DBPROP_FILE}" "{DUSER}" $DUSER
    !insertmacro ReplaceInFile "$INSTDIR${ITRIVIO_DIR}${IROOT_DIR}${ISETPROP_DIR}${DBPROP_FILE}" "{DPSWD}" $DPSWD

SectionEnd
