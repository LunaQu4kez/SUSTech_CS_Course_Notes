`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////

module ioread (
    input			reset,				// reset, active high 复位信号 (高电平有效)
	input			ior,				// from Controller, 1 means read from input device(从控制器来的I/O读)
    input			switchctrl,			// means the switch is selected as input device (从memorio经过地址高端线获得的拨码开关模块片选)
    input	[15:0]	ioread_data_switch,	// the data from switch(从外设来的读数据，此处来自拨码开关)
    output	[15:0]	ioread_data 		// the data to memorio (将外设来的数据送给memorio)
);
    
    reg [15:0] ioread_data;
    
    always @* begin
        if (reset)
            ioread_data = 16'h0;
        else if (ior == 1) begin
            if (switchctrl == 1)
                ioread_data = ioread_data_switch;
            else
				ioread_data = ioread_data;
        end
    end
	
endmodule
