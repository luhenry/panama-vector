/*
 * Copyright (c) 2017, 2019, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have
 * questions.
 */
package jdk.incubator.vector;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ReadOnlyBufferException;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.IntUnaryOperator;

import jdk.internal.misc.Unsafe;
import jdk.internal.vm.annotation.ForceInline;
import jdk.internal.vm.annotation.Stable;

import static jdk.incubator.vector.VectorIntrinsics.*;
import static jdk.incubator.vector.VectorOperators.*;

// -- This file was mechanically generated: Do not edit! -- //

@SuppressWarnings("cast")  // warning: redundant cast
final class Float64Vector extends FloatVector {
    static final FloatSpecies VSPECIES =
        (FloatSpecies) FloatVector.SPECIES_64;

    static final VectorShape VSHAPE =
        VSPECIES.vectorShape();

    static final Class<Float64Vector> VCLASS = Float64Vector.class;

    static final int VSIZE = VSPECIES.vectorBitSize();

    static final int VLENGTH = VSPECIES.laneCount();

    static final Class<Float> ETYPE = float.class;

    // The JVM expects to find the state here.
    private final float[] vec; // Don't access directly, use getElements() instead.

    Float64Vector(float[] v) {
        vec = v;
    }

    // For compatibility as Float64Vector::new,
    // stored into species.vectorFactory.
    Float64Vector(Object v) {
        this((float[]) v);
    }

    static final Float64Vector ZERO = new Float64Vector(new float[VLENGTH]);
    static final Float64Vector IOTA = new Float64Vector(VSPECIES.iotaArray());

    static {
        // Warm up a few species caches.
        // If we do this too much we will
        // get NPEs from bootstrap circularity.
        VSPECIES.dummyVector();
        VSPECIES.withLanes(LaneType.BYTE);
    }

    // Specialized extractors

    @ForceInline
    final @Override
    public FloatSpecies vspecies() {
        // ISSUE:  This should probably be a @Stable
        // field inside AbstractVector, rather than
        // a megamorphic method.
        return VSPECIES;
    }


    /*package-private*/
    @ForceInline
    final @Override
    float[] getElements() {
        return VectorIntrinsics.maybeRebox(this).vec;
    }

    // Virtualized constructors

    @Override
    @ForceInline
    public final Float64Vector broadcast(float e) {
        return (Float64Vector) super.broadcastTemplate(e);  // specialize
    }

    @Override
    @ForceInline
    public final Float64Vector broadcast(long e) {
        return (Float64Vector) super.broadcastTemplate(e);  // specialize
    }

    @Override
    @ForceInline
    Float64Mask maskFromArray(boolean[] bits) {
        return new Float64Mask(bits);
    }

    @Override
    @ForceInline
    Float64Shuffle iotaShuffle() { return Float64Shuffle.IOTA; }

    @ForceInline
    Float64Shuffle iotaShuffle(int start) { 
        return (Float64Shuffle)VectorIntrinsics.shuffleIota(ETYPE, Float64Shuffle.class, VSPECIES, VLENGTH, start, (val, l) -> new Float64Shuffle(i -> (Float64Shuffle.partiallyWrapIndex(i + val, l))));
    }

    @Override
    @ForceInline
    Float64Shuffle shuffleFromBytes(byte[] reorder) { return new Float64Shuffle(reorder); }

    @Override
    @ForceInline
    Float64Shuffle shuffleFromArray(int[] indexes, int i) { return new Float64Shuffle(indexes, i); }

    @Override
    @ForceInline
    Float64Shuffle shuffleFromOp(IntUnaryOperator fn) { return new Float64Shuffle(fn); }

    // Make a vector of the same species but the given elements:
    @ForceInline
    final @Override
    Float64Vector vectorFactory(float[] vec) {
        return new Float64Vector(vec);
    }

    @ForceInline
    final @Override
    Byte64Vector asByteVectorRaw() {
        return (Byte64Vector) super.asByteVectorRawTemplate();  // specialize
    }

    @ForceInline
    final @Override
    AbstractVector<?> asVectorRaw(LaneType laneType) {
        return super.asVectorRawTemplate(laneType);  // specialize
    }

    // Unary operator

    final @Override
    Float64Vector uOp(FUnOp f) {
        return (Float64Vector) super.uOpTemplate(f);  // specialize
    }

    @ForceInline
    final @Override
    Float64Vector uOp(VectorMask<Float> m, FUnOp f) {
        return (Float64Vector)
            super.uOpTemplate((Float64Mask)m, f);  // specialize
    }

    // Binary operator

    @ForceInline
    final @Override
    Float64Vector bOp(Vector<Float> v, FBinOp f) {
        return (Float64Vector) super.bOpTemplate((Float64Vector)v, f);  // specialize
    }

    @ForceInline
    final @Override
    Float64Vector bOp(Vector<Float> v,
                     VectorMask<Float> m, FBinOp f) {
        return (Float64Vector)
            super.bOpTemplate((Float64Vector)v, (Float64Mask)m,
                              f);  // specialize
    }

    // Ternary operator

    @ForceInline
    final @Override
    Float64Vector tOp(Vector<Float> v1, Vector<Float> v2, FTriOp f) {
        return (Float64Vector)
            super.tOpTemplate((Float64Vector)v1, (Float64Vector)v2,
                              f);  // specialize
    }

    @ForceInline
    final @Override
    Float64Vector tOp(Vector<Float> v1, Vector<Float> v2,
                     VectorMask<Float> m, FTriOp f) {
        return (Float64Vector)
            super.tOpTemplate((Float64Vector)v1, (Float64Vector)v2,
                              (Float64Mask)m, f);  // specialize
    }

    @ForceInline
    final @Override
    float rOp(float v, FBinOp f) {
        return super.rOpTemplate(v, f);  // specialize
    }

    @Override
    @ForceInline
    public final <F>
    Vector<F> convertShape(VectorOperators.Conversion<Float,F> conv,
                           VectorSpecies<F> rsp, int part) {
        return super.convertShapeTemplate(conv, rsp, part);  // specialize
    }

    @Override
    @ForceInline
    public final <F>
    Vector<F> reinterpretShape(VectorSpecies<F> toSpecies, int part) {
        return super.reinterpretShapeTemplate(toSpecies, part);  // specialize
    }

    // Specialized algebraic operations:

    // The following definition forces a specialized version of this
    // crucial method into the v-table of this class.  A call to add()
    // will inline to a call to lanewise(ADD,), at which point the JIT
    // intrinsic will have the opcode of ADD, plus all the metadata
    // for this particular class, enabling it to generate precise
    // code.
    //
    // There is probably no benefit to the JIT to specialize the
    // masked or broadcast versions of the lanewise method.

    @Override
    @ForceInline
    public Float64Vector lanewise(Unary op) {
        return (Float64Vector) super.lanewiseTemplate(op);  // specialize
    }

    @Override
    @ForceInline
    public Float64Vector lanewise(Binary op, Vector<Float> v) {
        return (Float64Vector) super.lanewiseTemplate(op, v);  // specialize
    }


    /*package-private*/
    @Override
    @ForceInline
    public final
    Float64Vector
    lanewise(VectorOperators.Ternary op, Vector<Float> v1, Vector<Float> v2) {
        return (Float64Vector) super.lanewiseTemplate(op, v1, v2);  // specialize
    }

    @Override
    @ForceInline
    public final
    Float64Vector addIndex(int scale) {
        return (Float64Vector) super.addIndexTemplate(scale);  // specialize
    }

    // Type specific horizontal reductions

    @Override
    @ForceInline
    public final float reduceLanes(VectorOperators.Associative op) {
        return super.reduceLanesTemplate(op);  // specialized
    }

    @Override
    @ForceInline
    public final float reduceLanes(VectorOperators.Associative op,
                                    VectorMask<Float> m) {
        return super.reduceLanesTemplate(op, m);  // specialized
    }

    @Override
    @ForceInline
    public final long reduceLanesToLong(VectorOperators.Associative op) {
        return (long) super.reduceLanesTemplate(op);  // specialized
    }

    @Override
    @ForceInline
    public final long reduceLanesToLong(VectorOperators.Associative op,
                                        VectorMask<Float> m) {
        return (long) super.reduceLanesTemplate(op, m);  // specialized
    }

    @Override
    @ForceInline
    public VectorShuffle<Float> toShuffle() {
        float[] a = toArray();
        int[] sa = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            sa[i] = (int) a[i];
        }
        return VectorShuffle.fromArray(VSPECIES, sa, 0);
    }

    // Specialized comparisons

    @Override
    @ForceInline
    public final Float64Mask compare(Comparison op, Vector<Float> v) {
        return super.compareTemplate(Float64Mask.class, op, v);  // specialize
    }

    @Override
    @ForceInline
    public final Float64Mask compare(Comparison op, float s) {
        return super.compareTemplate(Float64Mask.class, op, s);  // specialize
    }

    @Override
    @ForceInline
    public final Float64Mask compare(Comparison op, long s) {
        return super.compareTemplate(Float64Mask.class, op, s);  // specialize
    }

    @Override
    @ForceInline
    public Float64Vector blend(Vector<Float> v, VectorMask<Float> m) {
        return (Float64Vector)
            super.blendTemplate(Float64Mask.class,
                                (Float64Vector) v,
                                (Float64Mask) m);  // specialize
    }

    @Override
    @ForceInline
    public Float64Vector slice(int origin, Vector<Float> v) {
        return (Float64Vector) super.sliceTemplate(origin, v);  // specialize
    }

    @Override
    @ForceInline
    public Float64Vector slice(int origin) {
       if ((origin < 0) || (origin >= VLENGTH)) {
         throw new ArrayIndexOutOfBoundsException("Index " + origin + " out of bounds for vector length " + VLENGTH);
       } else {
         Float64Shuffle Iota = iotaShuffle(origin);
         VectorMask<Float> BlendMask = Iota.toVector().compare(VectorOperators.GE, (broadcast((float)(origin))));
         Iota = iotaShuffle(origin);
         return ZERO.blend(this.rearrange(Iota), BlendMask);
       }
    }

    @Override
    @ForceInline
    public Float64Vector unslice(int origin, Vector<Float> w, int part) {
        return (Float64Vector) super.unsliceTemplate(origin, w, part);  // specialize
    }

    @Override
    @ForceInline
    public Float64Vector unslice(int origin, Vector<Float> w, int part, VectorMask<Float> m) {
        return (Float64Vector)
            super.unsliceTemplate(Float64Mask.class,
                                  origin, w, part,
                                  (Float64Mask) m);  // specialize
    }

    @Override
    @ForceInline
    public Float64Vector unslice(int origin) {
       if ((origin < 0) || (origin >= VLENGTH)) {
         throw new ArrayIndexOutOfBoundsException("Index " + origin + " out of bounds for vector length " + VLENGTH);
       } else {
         Float64Shuffle Iota = iotaShuffle(-origin);
         VectorMask<Float> BlendMask = Iota.toVector().compare(VectorOperators.GE, (broadcast((float)(0))));
         Iota = iotaShuffle(-origin);
         return ZERO.blend(this.rearrange(Iota), BlendMask);
       }
    }

    @Override
    @ForceInline
    public Float64Vector rearrange(VectorShuffle<Float> s) {
        return (Float64Vector)
            super.rearrangeTemplate(Float64Shuffle.class,
                                    (Float64Shuffle) s);  // specialize
    }

    @Override
    @ForceInline
    public Float64Vector rearrange(VectorShuffle<Float> shuffle,
                                  VectorMask<Float> m) {
        return (Float64Vector)
            super.rearrangeTemplate(Float64Shuffle.class,
                                    (Float64Shuffle) shuffle,
                                    (Float64Mask) m);  // specialize
    }

    @Override
    @ForceInline
    public Float64Vector rearrange(VectorShuffle<Float> s,
                                  Vector<Float> v) {
        return (Float64Vector)
            super.rearrangeTemplate(Float64Shuffle.class,
                                    (Float64Shuffle) s,
                                    (Float64Vector) v);  // specialize
    }

    @Override
    @ForceInline
    public Float64Vector selectFrom(Vector<Float> v) {
        return (Float64Vector)
            super.selectFromTemplate((Float64Vector) v);  // specialize
    }

    @Override
    @ForceInline
    public Float64Vector selectFrom(Vector<Float> v,
                                   VectorMask<Float> m) {
        return (Float64Vector)
            super.selectFromTemplate((Float64Vector) v,
                                     (Float64Mask) m);  // specialize
    }


    @Override
    public float lane(int i) {
        if (i < 0 || i >= VLENGTH) {
            throw new IllegalArgumentException("Index " + i + " must be zero or positive, and less than " + VLENGTH);
        }
        int bits = (int) VectorIntrinsics.extract(
                                VCLASS, ETYPE, VLENGTH,
                                this, i,
                                (vec, ix) -> {
                                    float[] vecarr = vec.getElements();
                                    return (long)Float.floatToIntBits(vecarr[ix]);
                                });
        return Float.intBitsToFloat(bits);
    }

    @Override
    public Float64Vector withLane(int i, float e) {
        if (i < 0 || i >= VLENGTH) {
            throw new IllegalArgumentException("Index " + i + " must be zero or positive, and less than " + VLENGTH);
        }
        return VectorIntrinsics.insert(
                                VCLASS, ETYPE, VLENGTH,
                                this, i, (long)Float.floatToIntBits(e),
                                (v, ix, bits) -> {
                                    float[] res = v.getElements().clone();
                                    res[ix] = Float.intBitsToFloat((int)bits);
                                    return v.vectorFactory(res);
                                });
    }

    // Mask

    static final class Float64Mask extends AbstractMask<Float> {

        private final boolean[] bits; // Don't access directly, use getBits() instead.

        public Float64Mask(boolean[] bits) {
            this(bits, 0);
        }

        public Float64Mask(boolean[] bits, int offset) {
            boolean[] a = new boolean[vspecies().laneCount()];
            for (int i = 0; i < a.length; i++) {
                a[i] = bits[offset + i];
            }
            this.bits = a;
        }

        public Float64Mask(boolean val) {
            boolean[] bits = new boolean[vspecies().laneCount()];
            Arrays.fill(bits, val);
            this.bits = bits;
        }

        @ForceInline
        final @Override
        public FloatSpecies vspecies() {
            // ISSUE:  This should probably be a @Stable
            // field inside AbstractMask, rather than
            // a megamorphic method.
            return VSPECIES;
        }

        boolean[] getBits() {
            return VectorIntrinsics.maybeRebox(this).bits;
        }

        @Override
        Float64Mask uOp(MUnOp f) {
            boolean[] res = new boolean[vspecies().laneCount()];
            boolean[] bits = getBits();
            for (int i = 0; i < res.length; i++) {
                res[i] = f.apply(i, bits[i]);
            }
            return new Float64Mask(res);
        }

        @Override
        Float64Mask bOp(VectorMask<Float> m, MBinOp f) {
            boolean[] res = new boolean[vspecies().laneCount()];
            boolean[] bits = getBits();
            boolean[] mbits = ((Float64Mask)m).getBits();
            for (int i = 0; i < res.length; i++) {
                res[i] = f.apply(i, bits[i], mbits[i]);
            }
            return new Float64Mask(res);
        }

        @ForceInline
        @Override
        public final
        Float64Vector toVector() {
            return (Float64Vector) super.toVectorTemplate();  // specialize
        }

        @Override
        @ForceInline
        public <E> VectorMask<E> cast(VectorSpecies<E> s) {
            AbstractSpecies<E> species = (AbstractSpecies<E>) s;
            if (length() != species.laneCount())
                throw new IllegalArgumentException("VectorMask length and species length differ");
            boolean[] maskArray = toArray();
            // enum-switches don't optimize properly JDK-8161245
            switch (species.laneType.switchKey) {
            case LaneType.SK_BYTE:
                return new Byte64Vector.Byte64Mask(maskArray).check(species);
            case LaneType.SK_SHORT:
                return new Short64Vector.Short64Mask(maskArray).check(species);
            case LaneType.SK_INT:
                return new Int64Vector.Int64Mask(maskArray).check(species);
            case LaneType.SK_LONG:
                return new Long64Vector.Long64Mask(maskArray).check(species);
            case LaneType.SK_FLOAT:
                return new Float64Vector.Float64Mask(maskArray).check(species);
            case LaneType.SK_DOUBLE:
                return new Double64Vector.Double64Mask(maskArray).check(species);
            }

            // Should not reach here.
            throw new AssertionError(species);
        }

        // Unary operations

        @Override
        @ForceInline
        public Float64Mask not() {
            return (Float64Mask) VectorIntrinsics.unaryOp(
                                             VECTOR_OP_NOT, Float64Mask.class, int.class, VLENGTH,
                                             this,
                                             (m1) -> m1.uOp((i, a) -> !a));
        }

        // Binary operations

        @Override
        @ForceInline
        public Float64Mask and(VectorMask<Float> mask) {
            Objects.requireNonNull(mask);
            Float64Mask m = (Float64Mask)mask;
            return VectorIntrinsics.binaryOp(VECTOR_OP_AND, Float64Mask.class, int.class, VLENGTH,
                                             this, m,
                                             (m1, m2) -> m1.bOp(m2, (i, a, b) -> a & b));
        }

        @Override
        @ForceInline
        public Float64Mask or(VectorMask<Float> mask) {
            Objects.requireNonNull(mask);
            Float64Mask m = (Float64Mask)mask;
            return VectorIntrinsics.binaryOp(VECTOR_OP_OR, Float64Mask.class, int.class, VLENGTH,
                                             this, m,
                                             (m1, m2) -> m1.bOp(m2, (i, a, b) -> a | b));
        }

        // Reductions

        @Override
        @ForceInline
        public boolean anyTrue() {
            return VectorIntrinsics.test(BT_ne, Float64Mask.class, int.class, VLENGTH,
                                         this, this,
                                         (m, __) -> anyTrueHelper(((Float64Mask)m).getBits()));
        }

        @Override
        @ForceInline
        public boolean allTrue() {
            return VectorIntrinsics.test(BT_overflow, Float64Mask.class, int.class, VLENGTH,
                                         this, vspecies().maskAll(true),
                                         (m, __) -> allTrueHelper(((Float64Mask)m).getBits()));
        }

        /*package-private*/
        static Float64Mask maskAll(boolean bit) {
            return bit ? TRUE_MASK : FALSE_MASK;
        }
        static final Float64Mask TRUE_MASK = new Float64Mask(true);
        static final Float64Mask FALSE_MASK = new Float64Mask(false);
    }

    // Shuffle

    static final class Float64Shuffle extends AbstractShuffle<Float> {
        Float64Shuffle(byte[] reorder) {
            super(reorder);
        }

        public Float64Shuffle(int[] reorder) {
            super(reorder);
        }

        public Float64Shuffle(int[] reorder, int i) {
            super(reorder, i);
        }

        public Float64Shuffle(IntUnaryOperator fn) {
            super(fn);
        }

        @Override
        public FloatSpecies vspecies() {
            return VSPECIES;
        }

        static {
            // There must be enough bits in the shuffle lanes to encode
            // VLENGTH valid indexes and VLENGTH exceptional ones.
            assert(VLENGTH < Byte.MAX_VALUE);
            assert(Byte.MIN_VALUE <= -VLENGTH);
        }
        static final Float64Shuffle IOTA = new Float64Shuffle(IDENTITY);

        @Override
        @ForceInline
        public Float64Vector toVector() {
            return VectorIntrinsics.shuffleToVector(VCLASS, ETYPE, Float64Shuffle.class, this, VLENGTH,
                                                    (s) -> ((Float64Vector)(((AbstractShuffle<Float>)(s)).toVectorTemplate())));
        }

        @Override
        @ForceInline
        public <F> VectorShuffle<F> cast(VectorSpecies<F> s) {
            AbstractSpecies<F> species = (AbstractSpecies<F>) s;
            if (length() != species.laneCount())
                throw new AssertionError("NYI: Shuffle length and species length differ");
            int[] shuffleArray = toArray();
            // enum-switches don't optimize properly JDK-8161245
            switch (species.laneType.switchKey) {
            case LaneType.SK_BYTE:
                return new Byte64Vector.Byte64Shuffle(shuffleArray).check(species);
            case LaneType.SK_SHORT:
                return new Short64Vector.Short64Shuffle(shuffleArray).check(species);
            case LaneType.SK_INT:
                return new Int64Vector.Int64Shuffle(shuffleArray).check(species);
            case LaneType.SK_LONG:
                return new Long64Vector.Long64Shuffle(shuffleArray).check(species);
            case LaneType.SK_FLOAT:
                return new Float64Vector.Float64Shuffle(shuffleArray).check(species);
            case LaneType.SK_DOUBLE:
                return new Double64Vector.Double64Shuffle(shuffleArray).check(species);
            }

            // Should not reach here.
            throw new AssertionError(species);
        }

        @Override
        public Float64Shuffle rearrange(VectorShuffle<Float> shuffle) {
            Float64Shuffle s = (Float64Shuffle) shuffle;
            byte[] r = new byte[reorder.length];
            for (int i = 0; i < reorder.length; i++) {
                int ssi = s.reorder[i];
                r[i] = this.reorder[ssi];  // throws on exceptional index
            }
            return new Float64Shuffle(r);
        }
    }

    // ================================================

    // Specialized low-level memory operations.

    @ForceInline
    @Override
    final
    FloatVector fromArray0(float[] a, int offset) {
        return super.fromArray0Template(a, offset);  // specialize
    }

    @ForceInline
    @Override
    final
    FloatVector fromByteArray0(byte[] a, int offset) {
        return super.fromByteArray0Template(a, offset);  // specialize
    }

    @ForceInline
    @Override
    final
    FloatVector fromByteBuffer0(ByteBuffer bb, int offset) {
        return super.fromByteBuffer0Template(bb, offset);  // specialize
    }

    @ForceInline
    @Override
    final
    void intoArray0(float[] a, int offset) {
        super.intoArray0Template(a, offset);  // specialize
    }

    @ForceInline
    @Override
    final
    void intoByteArray0(byte[] a, int offset) {
        super.intoByteArray0Template(a, offset);  // specialize
    }

    // End of specialized low-level memory operations.

    // ================================================

}
