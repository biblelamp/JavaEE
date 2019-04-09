OutFile nsDialogs.exe
RequestExecutionLevel user

!include nsDialogs.nsh

XPStyle on

;Var Dialog
;Var Label1
;Var Label2
Var IPAddr
;Var VPNName

Page Custom nsDialogsPage nsDialogsPageLeave
Page InstFiles

Function .onInit

    StrCpy $IPAddr "http://127.0.0.1/"

FunctionEnd

Function nsDialogsPage

    nsDialogs::Create 1018
    Pop $0

    ${NSD_CreateLabel} 0 0 100% 12u "What's Your IP Address on the VPN?"
    Pop $0

    ${NSD_CreateText} 0 12u 100% 12u $IPAddr
    Pop $IPAddr

    nsDialogs::Show

FunctionEnd

Function nsDialogsPageLeave

    ${NSD_GetText} $IPAddr $0
    MessageBox mb_ok $0

    ;nsDialogs::Create 1018
    ;Pop $0

    ;${NSD_CreateLabel} 0 0 100% 12u "What's Your IP Address on the VPN?"
    ;Pop $Label1

    ;${NSD_CreateText} 0 12u 100% 12u "$0"
    ;Pop $IPAddr

    ;nsDialogs::Show

FunctionEnd

Section
SectionEnd
