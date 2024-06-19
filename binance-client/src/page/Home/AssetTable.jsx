import React from 'react'
import { Avatar, AvatarImage } from "@/components/ui/avatar";

import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";

export const AssetTable = () => {
  return (
    <Table className="px-5  border-t relative">
      <TableHeader>
        <TableRow className="sticky top-0 left-0 right-0 bg-background">
          <TableHead className="py-4">Name</TableHead>
          <TableHead>SYMBOL</TableHead>
          <TableHead>VOLUME</TableHead>
          <TableHead>MARKET CAP</TableHead>
          <TableHead>24H</TableHead>
          <TableHead className="text-right">PRICE</TableHead>
        </TableRow>
      </TableHeader>

      <TableBody>
        {[1, 1, 1, 1, 1, 1, 1].map((item, index) => (
          <TableRow className="cursor-pointer" key={index}>
            <TableCell className="font-medium flex items-center gap-2">
              <Avatar className="-z-50">
                <AvatarImage src="https://png.pngtree.com/png-vector/20200902/ourmid/pngtree-golden-dollar-coin-money-png-image_2337468.jpg" />
              </Avatar>
              <span> Bitcoin</span>
            </TableCell>
            <TableCell>BTC</TableCell>
            <TableCell>12436443745</TableCell>
            <TableCell>-436443745</TableCell>
            <TableCell>+1134.56%</TableCell>
            <TableCell className="text-right">$234.56</TableCell>
          </TableRow>
        ))}
      </TableBody>
    </Table>
  )
}
