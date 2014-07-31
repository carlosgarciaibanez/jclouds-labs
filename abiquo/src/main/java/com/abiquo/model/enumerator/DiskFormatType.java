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

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.StringUtils;

@XmlType(name = "diskFormatType")
@XmlEnum
public enum DiskFormatType
{
	/* 0 */
	UNKNOWN("http://unknown", "Unknown format", DiskFormatTypeAlias.UNKNOWN),

	/* 1 */
	RAW("http://raw", "Disk format device", DiskFormatTypeAlias.RAW),

	/* 2 */
	INCOMPATIBLE("http://incompatible", "Incompatible disk type", DiskFormatTypeAlias.INCOMPATIBLE),

	/* 3 */
	VMDK_STREAM_OPTIMIZED(
			"http://www.vmware.com/technical-resources/interfaces/vmdk_access.html#streamOptimized",
			"VMWare streamOptimized format", DiskFormatTypeAlias.VMDK_STREAM_OPTIMIZED),

			/* 4 */
			VMDK_FLAT(
					"http://www.vmware.com/technical-resources/interfaces/vmdk_access.html#monolithic_flat",
					"VMWare Fixed Disk", DiskFormatTypeAlias.VMDK_FLAT),

					/* 5 */
					VMDK_SPARSE(
							"http://www.vmware.com/technical-resources/interfaces/vmdk_access.html#monolithic_sparse",
							"VMWare Sparse Disk", DiskFormatTypeAlias.VMDK_SPARSE),

							/* 6 */
							VHD_FLAT("http://technet.microsoft.com/en-us/virtualserver/bb676673.aspx#monolithic_flat",
									"VHD Fixed Disk", DiskFormatTypeAlias.VHD, "vhd"),

									/* 7 */
									VHD_SPARSE("http://technet.microsoft.com/en-us/virtualserver/bb676673.aspx#monolithic_sparse",
											"VHD Sparse Disk", DiskFormatTypeAlias.VHD, "vhd"),

											/* 8 */
											VDI_FLAT("http://forums.virtualbox.org/viewtopic.php?t=8046#monolithic_flat", "VDI Fixed disk",
													DiskFormatTypeAlias.VDI),

													/* 9 */
													VDI_SPARSE("http://forums.virtualbox.org/viewtopic.php?t=8046#monolithic_sparse",
															"VDI Sparse disk", DiskFormatTypeAlias.VDI),

															/* 10 */
															QCOW2_FLAT("http://people.gnome.org/~markmc/qcow-image-format.html#monolithic_flat",
																	"QCOW2 Fixed disk", DiskFormatTypeAlias.QCOW2),

																	/* 11 */
																	QCOW2_SPARSE("http://people.gnome.org/~markmc/qcow-image-format.html#monolithic_sparse",
																			"QCOW2 Sparse disk", DiskFormatTypeAlias.QCOW2);

	public final String uri, description;

	public final DiskFormatTypeAlias alias;

	public final String extension;

	public static final DiskFormatType[] VBOX_COMPATIBLES = new DiskFormatType[] {VMDK_SPARSE,
		VHD_FLAT, VHD_SPARSE, VDI_FLAT, VDI_SPARSE};

	public static final DiskFormatType[] KVM_COMPATIBLES = new DiskFormatType[] {RAW, VMDK_SPARSE,
		VMDK_FLAT, VHD_FLAT, VHD_SPARSE, QCOW2_FLAT, QCOW2_SPARSE};

	public static final DiskFormatType[] XEN_COMPATIBLES = new DiskFormatType[] {VMDK_FLAT};

	public static final DiskFormatType[] VMWARE_COMPATIBLES = new DiskFormatType[] {VMDK_FLAT,
		VMDK_SPARSE};

	public static final DiskFormatType[] HYPERV_COMPATIBLES = new DiskFormatType[] {VHD_FLAT,
		VHD_SPARSE};

	public static final DiskFormatType[] XENSERVER_COMPATIBLES = HYPERV_COMPATIBLES;

	/* package */final static int ID_MIN = 0;

	/* package */private final static int ID_MAX = 11;

	private DiskFormatType(final String uri, final String description,
			final DiskFormatTypeAlias alias)
	{
		this.uri = uri;
		this.description = description;
		this.alias = alias;
		this.extension = StringUtils.EMPTY;
	}

	private DiskFormatType(final String uri, final String description,
			final DiskFormatTypeAlias alias, final String extension)
	{
		this.uri = uri;
		this.description = description;
		this.alias = alias;
		this.extension = extension;
	}

	public DiskFormatTypeAlias getAlias()
	{
		return alias;
	}

	public String getUri()
	{
		return uri;
	}

	public String getDescription()
	{
		return description;
	}

	public int id()
	{
		return ordinal();
	}

	public static DiskFormatType fromId(final int id)
	{
		return values()[id];
	}

	public static DiskFormatType fromURI(final String URI)
	{
		for (DiskFormatType type : values())
		{
			if (type.uri.equals(URI))
			{
				return type;
			}
		}
		return null;
	}

	public static DiskFormatType fromValue(final String v)
	{
		return valueOf(v);
	}

	public static int getIdMax()
	{
		return ID_MAX;
	}

	public boolean requiresExtension()
	{
		return !StringUtils.isBlank(extension);
	}
}