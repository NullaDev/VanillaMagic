package com.nulladev.ic.logic;

public interface LogicGate1 {

    LogicGate1 NOT = a -> ~a;
    LogicGate1 U_OR = a -> a != 0 ? 1 : 0;
    LogicGate1 U_AND = a -> a == -1 ? 1 : 0;

    int calculate(int a);

}
