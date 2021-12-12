package com.nulladev.ic.logic;

public interface LogicGate2 {

    LogicGate2 AND = (a, b) -> a & b;
    LogicGate2 OR = (a, b) -> a | b;
    LogicGate2 XOR = (a, b) -> a ^ b;
    LogicGate2 NAND = (a, b) -> ~(a & b);
    LogicGate2 NOR = (a, b) -> ~(a | b);
    LogicGate2 EQ = (a, b) -> ~(a ^ b);

    int calculate(int a, int b);

}
