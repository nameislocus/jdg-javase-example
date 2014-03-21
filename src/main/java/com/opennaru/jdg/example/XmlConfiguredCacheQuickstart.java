/*
 * Opennaru, Inc. http://www.opennaru.com/
 *  
 * Copyright 2014 Opennaru, Inc. and/or its affiliates.
 * All rights reserved by Opennaru, Inc.
 * 
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package com.opennaru.jdg.example;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;

/**
 * 
 * @author Junshik Jeon(service@opennaru.com, nameislocus@gmail.com)
 *
 */
public class XmlConfiguredCacheQuickstart {

	public static void main(String args[]) throws Exception {
		System.setProperty("java.net.preferIPv4Stack", "true");
		System.setProperty("jgroups.bind_addr", "127.0.0.1");
		System.setProperty("jgroups.udp.mcast_addr", "228.2.2.2");
		System.setProperty("jgroups.udp.mcast_port", "46655");
		
		
		EmbeddedCacheManager manager = new DefaultCacheManager("infinispan-distribution.xml");
		Cache<Object, Object> cache = manager.getCache("testCache");

		System.out.println("cache=" + cache);
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			System.out.println("COMMAND=put,get,remove,size,clear,putWithTime,putIfAbsent");
			System.out.print(">> ");
			String cmd = scanner.nextLine();
			cmd = cmd.toUpperCase();
			
			if( cmd.equals("PUT") ) {
				System.out.print("KEY=");
				String key = scanner.nextLine();
				System.out.print("VALUE=");
				String value = scanner.nextLine();
				
				cache.put(key, value);
				
			} else if( cmd.equals("GET") ) {
				System.out.print("KEY=");
				String key = scanner.nextLine();

				System.out.println("VALUE=" + cache.get(key));				
			
			} else if( cmd.equals("REMOVE")) {
				System.out.print("KEY=");
				String key = scanner.nextLine();
				cache.remove(key);
			
			} else if( cmd.equals("CLEAR")) {
				cache.clear();				
			
			} else if( cmd.equals("SIZE")) {
				System.out.println("SIZE=" + cache.size());
			
			} else if( cmd.equals("PUTWITHTIME")) {
				System.out.print("KEY=");
				String key = scanner.nextLine();
				System.out.print("VALUE=");
				String value = scanner.nextLine();

				cache.put("key", "value", 5, TimeUnit.SECONDS);
			} else if( cmd.equals("PUTIFABSENT") ) {
				System.out.print("KEY=");
				String key = scanner.nextLine();
				System.out.print("VALUE=");
				String value = scanner.nextLine();
				
				cache.putIfAbsent(key, value);
			}

		}
	}

}
