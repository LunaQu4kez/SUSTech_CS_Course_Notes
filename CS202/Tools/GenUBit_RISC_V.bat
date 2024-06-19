@echo off
REM Generate UART download bit stream for RISC-V.
REM UARTCoe_v3.0.exe is used
REM rars2coe.exe is used
set program1=rars2coe.exe
set path11=inst.txt
set path10=dmem.txt
set program2=UARTCoe_v3.0.exe
set param2=h
set path21=prgmip32.coe
set path20=dmem32.coe
%program1%  %path11%  %path21%
%program1%  %path10%  %path20%
%program2% %param2% %path21% %path20% out.txt
@echo on
