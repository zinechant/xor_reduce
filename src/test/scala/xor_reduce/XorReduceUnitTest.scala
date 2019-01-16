// See README.md for license details.

package xor_reduce

import java.io.File

import chisel3.iotesters
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class XorReduceUnitTester(c: XorReduce) extends PeekPokeTester(c) {
  /**
    * compute the xor_reduce and the number of steps it should take to do it
    *
    * @param a positive integer
    * @param b positive integer
    * @return the XorReduce of a and b
    */

  private val xor_reduce = c

  for (i <- 0 until 2) for (j <- 0 until 2) for (k <- 0 until 2) for (l <- 0 until 2) {
    poke(xor_reduce.io.in1, IndexedSeq(BigInt(i), BigInt(j), BigInt(k), BigInt(l)))
    printf("xor.reduce.io.in1: %s\n", peek(xor_reduce.io.in1).toString())
    printf("xor.reduce.io.out1: %s\n", peek(xor_reduce.io.out1).toString())
  }
}

/**
  * This is a trivial example of how to run this Specification
  * From within sbt use:
  * {{{
  * testOnly xor_reduce.XorReduceTester
  * }}}
  * From a terminal shell use:
  * {{{
  * sbt 'testOnly xor_reduce.XorReduceTester'
  * }}}
  */
class XorReduceTester extends ChiselFlatSpec {
  // Disable this until we fix isCommandAvailable to swallow stderr along with stdout
  private val backendNames = if(false && firrtl.FileUtils.isCommandAvailable(Seq("verilator", "--version"))) {
    Array("firrtl", "verilator")
  }
  else {
    Array("firrtl")
  }
  for ( backendName <- backendNames ) {
    "XorReduce" should s"calculate proper greatest common denominator (with $backendName)" in {
      Driver(() => new XorReduce, backendName) {
        c => new XorReduceUnitTester(c)
      } should be (true)
    }
  }

  "Basic test using Driver.execute" should "be used as an alternative way to run specification" in {
    iotesters.Driver.execute(Array(), () => new XorReduce) {
      c => new XorReduceUnitTester(c)
    } should be (true)
  }

  "using --backend-name verilator" should "be an alternative way to run using verilator" in {
    if(backendNames.contains("verilator")) {
      iotesters.Driver.execute(Array("--backend-name", "verilator"), () => new XorReduce) {
        c => new XorReduceUnitTester(c)
      } should be(true)
    }
  }

  "running with --is-verbose" should "show more about what's going on in your tester" in {
    iotesters.Driver.execute(Array("--is-verbose"), () => new XorReduce) {
      c => new XorReduceUnitTester(c)
    } should be(true)
  }

  /**
    * By default verilator backend produces vcd file, and firrtl and treadle backends do not.
    * Following examples show you how to turn on vcd for firrtl and treadle and how to turn it off for verilator
    */

  "running with --generate-vcd-output on" should "create a vcd file from your test" in {
    iotesters.Driver.execute(
      Array("--generate-vcd-output", "on", "--target-dir", "test_run_dir/make_a_vcd", "--top-name", "make_a_vcd"),
      () => new XorReduce
    ) {

      c => new XorReduceUnitTester(c)
    } should be(true)

    new File("test_run_dir/make_a_vcd/make_a_vcd.vcd").exists should be (true)
  }

  "running with --generate-vcd-output off" should "not create a vcd file from your test" in {
    iotesters.Driver.execute(
      Array("--generate-vcd-output", "off", "--target-dir", "test_run_dir/make_no_vcd", "--top-name", "make_no_vcd",
      "--backend-name", "verilator"),
      () => new XorReduce
    ) {

      c => new XorReduceUnitTester(c)
    } should be(true)

    new File("test_run_dir/make_no_vcd/make_a_vcd.vcd").exists should be (false)

  }

}
