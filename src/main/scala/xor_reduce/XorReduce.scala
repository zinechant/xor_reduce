package xor_reduce
import chisel3._

class XorReduce extends Module{
  val DIM1:Int = 4
  val io = IO(new Bundle {
    val in1 = Input(Vec(DIM1, Bool()))
    val out1 = Output(Bool())
  })

  io.out1 := io.in1.asUInt.xorR
}
