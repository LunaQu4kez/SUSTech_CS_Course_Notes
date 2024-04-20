module adder (
    input [2:0] in1, in2,
    output [2:0] sum,
    output [0:0] overflow
);

    assign sum = in1 + in2;
    assign overflow = (in1[2] == in2[2]) ? ((in1[2] == 1'b1) ? (sum[2] == 1'b1 ? 1'b0 : 1'b1) : (sum[2] == 1'b1 ? 1'b1 : 1'b0)) : 1'b0;
    
endmodule

