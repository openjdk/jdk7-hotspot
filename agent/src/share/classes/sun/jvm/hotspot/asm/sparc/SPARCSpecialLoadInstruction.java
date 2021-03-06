/*
 * Copyright (c) 2002, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
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
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 *
 */

package sun.jvm.hotspot.asm.sparc;

import sun.jvm.hotspot.asm.*;
import sun.jvm.hotspot.utilities.Assert;

public class SPARCSpecialLoadInstruction
                        extends SPARCSpecialRegisterInstruction
                        implements /* imports */ SPARCSpecialRegisters {
    final private int specialReg;
    final private int cregNum;
    final private SPARCRegisterIndirectAddress addr;

    public SPARCSpecialLoadInstruction(String name, int specialReg, int cregNum,
                                             SPARCRegisterIndirectAddress addr) {
        super(name);
        this.specialReg = specialReg;
        this.cregNum = cregNum;
        this.addr = addr;
    }

    public SPARCSpecialLoadInstruction(String name, int specialReg, SPARCRegisterIndirectAddress addr) {
        this(name, specialReg, -1, addr);
    }

    public int getSpecialRegister() {
        return specialReg;
    }

    public int getCoprocessorRegister() {
        if (Assert.ASSERTS_ENABLED)
            Assert.that(specialReg == CREG, "not a coprocesssor register");
        return cregNum;
    }

    public Address getSource() {
        return addr;
    }

    protected String getDescription() {
        StringBuffer buf = new StringBuffer();
        buf.append(getName());
        buf.append(spaces);
        buf.append(addr);
        buf.append(comma);
        if (specialReg == CREG) {
           buf.append("creg" + cregNum);
        } else {
           buf.append(getSpecialRegisterName(specialReg));
        }
        return buf.toString();
    }
}
