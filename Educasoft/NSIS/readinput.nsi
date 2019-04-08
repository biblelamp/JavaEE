OutFile readinput.exe
RequestExecutionLevel user

!include nsDialogs.nsh

Var HelloText

Page Custom DialogCreate DialogLeave
Page InstFiles

Function DialogCreate

    nsDialogs::Create 1018
    Pop $0

    ${NSD_CreateText} 10% 20u 80% 12u "Hello World"
    Pop $HelloText

    nsDialogs::Show

FunctionEnd

Function DialogLeave

    ${NSD_GetText} $HelloText $0
    MessageBox mb_ok $0
    Abort ;Don't move to next page (If the input was invalid etc)

FunctionEnd

Section
SectionEnd