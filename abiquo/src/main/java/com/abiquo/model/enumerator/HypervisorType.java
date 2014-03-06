/**
 * Licensed to Abiquo Holdings S.L. (Abiquo) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.abiquo.model.enumerator;

import static com.abiquo.model.enumerator.DiskFormatType.HYPERV_COMPATIBLES;
import static com.abiquo.model.enumerator.DiskFormatType.KVM_COMPATIBLES;
import static com.abiquo.model.enumerator.DiskFormatType.VBOX_COMPATIBLES;
import static com.abiquo.model.enumerator.DiskFormatType.VDI_FLAT;
import static com.abiquo.model.enumerator.DiskFormatType.VHD_SPARSE;
import static com.abiquo.model.enumerator.DiskFormatType.VMDK_FLAT;
import static com.abiquo.model.enumerator.DiskFormatType.VMWARE_COMPATIBLES;
import static com.abiquo.model.enumerator.DiskFormatType.XENSERVER_COMPATIBLES;
import static com.abiquo.model.enumerator.DiskFormatType.XEN_COMPATIBLES;


public enum HypervisorType
{
	VBOX(8889, VDI_FLAT, VBOX_COMPATIBLES, "Virtual Box"), KVM(8889, VMDK_FLAT, KVM_COMPATIBLES,
			"KVM"), XEN_3(8889, VMDK_FLAT, XEN_COMPATIBLES, "Xen"), VMX_04(443, VMDK_FLAT,
					VMWARE_COMPATIBLES, VMDK_FLAT, "ESXi"), HYPERV_301(5985, VHD_SPARSE, HYPERV_COMPATIBLES,
							"Hyper-V"), XENSERVER(9363, VHD_SPARSE, XENSERVER_COMPATIBLES, "Xen Server");

	public final int defaultPort;

	public DiskFormatType baseFormat;

	public DiskFormatType[] compatibilityTable;

	public DiskFormatType instanceFormat;

	public String friendlyName;

	/* package */private final static int ID_MAX = 6;

	private HypervisorType(final int defaultPort, final DiskFormatType baseFormat,
			final DiskFormatType[] compatibilityTable, final DiskFormatType instanceFormat,
			final String friendlyName)
	{
		this.defaultPort = defaultPort;
		this.baseFormat = baseFormat;
		this.compatibilityTable = compatibilityTable;
		this.instanceFormat = instanceFormat;
		this.friendlyName = friendlyName;
	}

	private HypervisorType(final int defaultPort, final DiskFormatType baseFormat,
			final DiskFormatType[] compatibilityTable, final String friendlyName)
	{
		this.defaultPort = defaultPort;
		this.baseFormat = baseFormat;
		this.compatibilityTable = compatibilityTable;
		this.friendlyName = friendlyName;
	}

	public int id()
	{
		return ordinal() + 1;
	}

	public boolean isInstanceFormatFixed()
	{
		return instanceFormat != null;
	}

	public DiskFormatType getInstanceFormat()
	{
		return instanceFormat;
	}

	public boolean isCompatible(final DiskFormatType type)
	{
		for (DiskFormatType compatible : compatibilityTable)
		{
			if (compatible == type)
			{
				return true;
			}
		}
		return false;
	}

	public static HypervisorType fromId(final int id)
	{
		return values()[id - 1];
	}

	public static int getIdMax()
	{
		return ID_MAX;
	}

	/**
	 * Create a new instance of the HypervisorType from its 'value'
	 * 
	 * @param v value
	 * @return
	 */
	 public static HypervisorType fromValue(final String v)
	{
		return HypervisorType.valueOf(v.toUpperCase());
	}

	 public String getValue()
	 {
		 return name();
	 }

	 public String getFriendlyName()
	 {
		 return friendlyName;
	 }

	 public boolean requiresCredentials()
	 {
		 switch (this)
		 {
		 case KVM:
		 case XEN_3:
			 return false;
		 default:
			 return true;
		 }
	 }

	 /**
	  * @return build a pseudo-random MAC address depending on the HypervisorType.
	  */
	 public String getRandomMacAddress()
	 {
		 switch (this)
		 {
		 case VMX_04:
			 String outMac = new String("00:50:56:");
			 for (int i = 0; i < 3; i++)
			 {
				 int a = (int) (Math.random() * 10000 % 255);

				 if (i == 0)
				 {
					 while (a >= 62)
					 {
						 a = (int) (Math.random() * 10000 % 255);
					 }
				 }

				 String tmpHex = Integer.toHexString(a);
				 if (tmpHex.length() == 1)
				 {
					 tmpHex = "0" + tmpHex;
				 }
				 outMac += tmpHex;

				 if (i < 2)
				 {
					 outMac += ":";
				 }
			 }
			 return outMac;
		 case XEN_3:
			 outMac = new String("00:16:3e:");
			 for (int i = 0; i < 3; i++)
			 {
				 int a = (int) (Math.random() * 10000 % 255);
				 String tmpHex = Integer.toHexString(a);
				 if (tmpHex.length() == 1)
				 {
					 tmpHex = "0" + tmpHex;
				 }
				 outMac += tmpHex;
				 if (i < 2)
				 {
					 outMac += ":";
				 }
			 }
			 return outMac;
		 case KVM:
			 outMac = new String("52:54:00:");
			 for (int i = 0; i < 3; i++)
			 {
				 int a = (int) (Math.random() * 10000 % 255);
				 String tmpHex = Integer.toHexString(a);
				 if (tmpHex.length() == 1)
				 {
					 tmpHex = "0" + tmpHex;
				 }
				 outMac += tmpHex;
				 if (i < 2)
				 {
					 outMac += ":";
				 }
			 }
			 return outMac;
		 case VBOX:
			 outMac = new String("080027");
			 for (int i = 0; i < 3; i++)
			 {
				 int a = (int) (Math.random() * 10000 % 255);
				 String tmpHex = Integer.toHexString(a);
				 if (tmpHex.length() == 1)
				 {
					 tmpHex = "0" + tmpHex;
				 }
				 outMac += tmpHex;
			 }
			 return outMac;
		 case HYPERV_301:
			 outMac = new String("00155D"); // Hyper-V prefix: 00:15:5D
			 for (int i = 0; i < 3; i++)
			 {
				 int a = (int) (Math.random() * 10000 % 255);
				 String tmpHex = Integer.toHexString(a);
				 if (tmpHex.length() == 1)
				 {
					 tmpHex = "0" + tmpHex;
				 }
				 outMac += tmpHex;
			 }
			 return outMac;
		 case XENSERVER:
			 // A user-specified MAC address should at least be a unicast MAC address (bit8=0),
			 // and probably locally administered (bit7=0). Basically the 2nd hex digit should be
			 // one
			 // of: 2, 6, A or E.
			 // The other bits in the 3 first octets doesn't matter.

			 outMac = new String("fe:32:32:");
			 for (int i = 0; i < 3; i++)
			 {
				 int a = (int) (Math.random() * 10000 % 255);
				 String tmpHex = Integer.toHexString(a);
				 if (tmpHex.length() == 1)
				 {
					 tmpHex = "0" + tmpHex;
				 }
				 outMac += tmpHex;
				 if (i < 2)
				 {
					 outMac += ":";
				 }
			 }
			 return outMac;
		 default:
			 return "";
		 }
	 }

	 public static Integer transformHypervisorTypeToInteger(final String hypervisorType)
	 {
		 if (hypervisorType.equalsIgnoreCase(HypervisorType.VBOX.getValue()))
		 {
			 return 1;
		 }
		 else if (hypervisorType.equalsIgnoreCase(HypervisorType.KVM.getValue()))
		 {
			 return 2;
		 }
		 else if (hypervisorType.equalsIgnoreCase(HypervisorType.XEN_3.getValue()))
		 {
			 return 3;
		 }
		 else if (hypervisorType.equalsIgnoreCase(HypervisorType.VMX_04.getValue()))
		 {
			 return 4;
		 }
		 else if (hypervisorType.equalsIgnoreCase(HypervisorType.HYPERV_301.getValue()))
		 {
			 return 5;
		 }
		 else if (hypervisorType.equalsIgnoreCase(HypervisorType.XENSERVER.getValue()))
		 {
			 return 6;
		 }

		 return null;

	 }

	 public static HypervisorType transformHypervisorTypeFromInteger(final int hypervisorType)
	 {
		 if (hypervisorType == 1)
		 {
			 return HypervisorType.VBOX;
		 }
		 else if (hypervisorType == 2)
		 {
			 return HypervisorType.KVM;
		 }
		 else if (hypervisorType == 3)
		 {
			 return HypervisorType.XEN_3;
		 }
		 else if (hypervisorType == 4)
		 {
			 return HypervisorType.VMX_04;
		 }
		 else if (hypervisorType == 5)
		 {
			 return HypervisorType.HYPERV_301;
		 }
		 else if (hypervisorType == 6)
		 {
			 return HypervisorType.XENSERVER;
		 }

		 return null;
	 }

	 /**
	  * @return false if the hypervisor type does not support hd. @deprecated This must be removed to
	  *         a plugin capability.
	  */
	 @Deprecated
	 public boolean isExtraHDSupported()
	 {
		 return this == VMX_04;
	 }
}
