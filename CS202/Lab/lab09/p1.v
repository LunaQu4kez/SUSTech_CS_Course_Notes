module m_inst (
    input           clk,
    input  [13:0]   addr,
    output [31:0]   dout
);

    prgrom urom(
        .clka(clk),
        .addra(addr),
        .douta(dout),
        .ena(1'b1)
    );
    
endmodule