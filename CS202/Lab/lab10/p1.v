module p1 (
    input [31:0] inst,
    output reg Branch,
    output reg [1:0] ALUOp,
    output reg ALUsrc,
    output reg MemRead,
    output reg MemWrite,
    output reg MemtoReg,
    output reg RegWrite
);

    always @(*) begin
        case (inst[6:0])
            7'b0110011: begin    // add, sub, and, or
                Branch   = 0;
                ALUOp    = 2;
                ALUsrc   = 0;
                MemRead  = 0;
                MemWrite = 0;
                MemtoReg = 0;
                RegWrite = 1;
            end
            7'b0000011: begin    // lw
                Branch   = 0;
                ALUOp    = 0;
                ALUsrc   = 1;
                MemRead  = 1;
                MemWrite = 0;
                MemtoReg = 1;
                RegWrite = 1;
            end
            7'b0100011: begin    // sw
                Branch   = 0;
                ALUOp    = 0;
                ALUsrc   = 1;
                MemRead  = 0;
                MemWrite = 1;
                MemtoReg = 0;
                RegWrite = 0;
            end
            7'b1100011: begin    // beq
                Branch   = 1;
                ALUOp    = 1;
                ALUsrc   = 1;
                MemRead  = 0;
                MemWrite = 0;
                MemtoReg = 0;
                RegWrite = 0;
            end
            default: begin
                Branch   = 0;
                ALUOp    = 0;
                ALUsrc   = 0;
                MemRead  = 0;
                MemWrite = 0;
                MemtoReg = 0;
                RegWrite = 0;
            end
        endcase
    end
    
endmodule