module subtractionTb();

    reg [2:0] in1, in2;
    wire overflow;
    wire [2:0] sum;

    subtraction ua(in1, in2, sum, overflow);

    initial begin
        {in1,in2} = 6'b0;
        repeat(63) #10 {in1,in2} = {in1,in2} + 1;
        #10 $finish;
    end

endmodule