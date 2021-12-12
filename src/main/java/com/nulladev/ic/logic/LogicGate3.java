package com.nulladev.ic.logic;

public interface LogicGate3 {

    LogicGate3 SWITCH = (a, b, c) -> c & a | ~c & b;

    int calculate(int a, int b, int c);

}
