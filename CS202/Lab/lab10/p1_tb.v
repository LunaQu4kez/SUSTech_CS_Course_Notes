module p1_tb ();
    
    reg [31:0] inst;
    wire Branch;
    wire [1:0] ALUOp;
    wire ALUsrc, MemRead, MemWrite, MemtoReg, RegWrite;

    p1 p1_inst(
        .inst(inst),
        .Branch(Branch),
        .ALUOp(ALUOp),
        .ALUsrc(ALUsrc),
        .MemRead(MemRead),
        .MemWrite(MemWrite),
        .MemtoReg(MemtoReg),
        .RegWrite(RegWrite)
    );

    initial begin
        inst = 32'h001080b3;
        #20 inst = 32'h403000b3;
        #20 inst = 32'h0041f133;
        #20 inst = 32'h005261b3;
        #20 inst = 32'h0000a083;
        #20 inst = 32'h00102423;
        #20 inst = 32'hfe1008e3;
        #20 $finish;
    end

endmodule