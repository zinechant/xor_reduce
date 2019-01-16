Chisel Project Testing The xorR Operator 
=======================

`sbt> test:runMain xor_reduce.XorReduceMain`

```
xor.reduce.io.in1: Vector(0, 0, 0, 0)
xor.reduce.io.out1: 0
xor.reduce.io.in1: Vector(1, 0, 0, 0)
xor.reduce.io.out1: 1
xor.reduce.io.in1: Vector(0, 1, 0, 0)
xor.reduce.io.out1: 0
xor.reduce.io.in1: Vector(1, 1, 0, 0)
xor.reduce.io.out1: 1
xor.reduce.io.in1: Vector(0, 0, 1, 0)
xor.reduce.io.out1: 0
xor.reduce.io.in1: Vector(1, 0, 1, 0)
xor.reduce.io.out1: 1
xor.reduce.io.in1: Vector(0, 1, 1, 0)
xor.reduce.io.out1: 0
xor.reduce.io.in1: Vector(1, 1, 1, 0)
xor.reduce.io.out1: 1
xor.reduce.io.in1: Vector(0, 0, 0, 1)
xor.reduce.io.out1: 0
xor.reduce.io.in1: Vector(1, 0, 0, 1)
xor.reduce.io.out1: 1
xor.reduce.io.in1: Vector(0, 1, 0, 1)
xor.reduce.io.out1: 0
xor.reduce.io.in1: Vector(1, 1, 0, 1)
xor.reduce.io.out1: 1
xor.reduce.io.in1: Vector(0, 0, 1, 1)
xor.reduce.io.out1: 0
xor.reduce.io.in1: Vector(1, 0, 1, 1)
xor.reduce.io.out1: 1
xor.reduce.io.in1: Vector(0, 1, 1, 1)
xor.reduce.io.out1: 0
xor.reduce.io.in1: Vector(1, 1, 1, 1)
xor.reduce.io.out1: 1
```